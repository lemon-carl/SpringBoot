package com.lemon.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lemon.server.mapper.MenuRoleMapper;
import com.lemon.server.model.MenuRole;
import com.lemon.server.pojo.common.RespBean;
import com.lemon.server.service.IMenuRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色菜单业务实现类
 *
 * @author lemon
 * @since 2021-04-07
 */
@Service
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements IMenuRoleService {
    @Autowired
    private MenuRoleMapper menuRoleMapper;

    @Override
    public List<Integer> getMidByRid(Integer rid) {
        QueryWrapper<MenuRole> queryWrapper = new QueryWrapper();
        queryWrapper.eq("rid", rid);
        List<Integer> list = this.list(queryWrapper)
                .stream().map(MenuRole::getMid).collect(Collectors.toList());
        return list;
    }

    @Override
    public RespBean updateMenuByRoleId(Integer rid, Integer[] mids) {
        QueryWrapper<MenuRole> queryWrapper = new QueryWrapper();
        queryWrapper.eq("rid", rid);
        menuRoleMapper.delete(queryWrapper);
        if (null == mids || mids.length == 0) {
            return RespBean.ok("更新成功！");
        }
        menuRoleMapper.insertRecord(rid, mids);

        return null;
    }
}
