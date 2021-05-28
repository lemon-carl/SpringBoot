package com.lemon.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lemon.server.mapper.RoleMapper;
import com.lemon.server.model.Role;
import com.lemon.server.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *  角色业务实现类
 *
 * @author lemon
 * @since 2021-04-07
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public int getRoleByNameOrNameZh(Role role) {
        return roleMapper.getRoleByNameOrNameZh(role);
    }
}
