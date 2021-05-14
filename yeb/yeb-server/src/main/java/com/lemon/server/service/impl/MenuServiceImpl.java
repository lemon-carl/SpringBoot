package com.lemon.server.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lemon.server.mapper.MenuMapper;
import com.lemon.server.pojo.Admin;
import com.lemon.server.pojo.Menu;
import com.lemon.server.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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
        Integer adminId = ((Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
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
}
