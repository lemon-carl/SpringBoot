package com.lemon.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lemon.server.model.Role;

/**
 * 角色业务层
 *
 * @author lemon
 * @since 2021-04-07
 */
public interface IRoleService extends IService<Role> {

    /**
     * 根据角色标识名或中文名 查询是否存在该角色
     *
     * @param role
     * @return
     */
    int getRoleByNameOrNameZh(Role role);
}
