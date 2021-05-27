package com.lemon.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lemon.server.model.Role;

import java.util.List;

/**
 * 角色 Mapper 接口
 *
 * @author lemon
 * @since 2021-04-07
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据用户Id查询角色列表
     * @param adminId
     * @return
     */
    List<Role> getRoles(Integer adminId);
}
