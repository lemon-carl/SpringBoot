package com.lemon.server.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.lemon.server.mapper.MenuMapper;
import com.lemon.server.model.Admin;
import com.lemon.server.model.Menu;
import com.lemon.server.pojo.vo.MenuVO;
import com.lemon.server.service.IMenuService;
import com.lemon.server.utils.AdminUtils;
import com.lemon.server.utils.BeanConvertUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *  菜单业务实现类
 *
 * @author lemon
 * @since 2021-04-07
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getMenusByAdminId() {
        Integer adminId = AdminUtils.getCurrentAdmin().getId();
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        // 从redis中获取菜单数据
        List<Menu> menus = (List<Menu>) valueOperations.get("menu_" + adminId);
        // 如果为空，从数据库中获取
        if (CollectionUtils.isEmpty(menus)) {
            menus = menuMapper.selectMenusByAdminId(adminId);
            // 将数据设置到redis中
            valueOperations.set("menu_" + adminId, menus);
        }
        return menus;
    }

    @Override
    public List<Menu> getMenusWithRole() {
        return menuMapper.getMenusWithRole();
    }


    @Override
    public List<MenuVO> getAllMenus() {
        List<Menu> allMenus = menuMapper.getAllMenus();
        List<MenuVO> returnList = new ArrayList<>();
        allMenus.forEach(menu -> {
            MenuVO menuVO = new MenuVO();
            BeanConvertUtil.copyBeanValue(menu, menuVO);
            // 递归菜单
            iterationMenuVO(menu, menuVO);

            returnList.add(menuVO);
        });


        return returnList;

    }

    private void iterationMenuVO(Menu menu, MenuVO menuVO) {
        List<MenuVO> childrenList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(menu.getChildren()) && menu.getChildren().size() > 0) {
            menu.getChildren().forEach(children -> {
                MenuVO childrenVO = new MenuVO();
                BeanConvertUtil.copyBeanValue(children, childrenVO);
                if (CollectionUtils.isNotEmpty(children.getChildren()) && children.getChildren().size() > 0) {
                    iterationMenuVO(children, childrenVO);
                }
                childrenList.add(childrenVO);
            });
            menuVO.setChildren(childrenList);
        }
    }
}
