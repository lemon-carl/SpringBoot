package com.lemon.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lemon.server.model.MenuRole;
import com.lemon.server.pojo.common.RespBean;

import java.util.List;

/**
 * 角色菜单关联业务
 *
 * @author lemon
 * @since 2021-04-07
 */
public interface IMenuRoleService extends IService<MenuRole> {

    /**
     * 根据角色id查询菜单id
     *
     * @param rid
     * @return
     */
    List<Integer> getMidByRid(Integer rid);

    /**
     * 更新角色菜单权限
     *
     * @param rid
     * @param mIds
     * @return
     */
    RespBean updateMenuByRoleId(Integer rid, Integer[] mIds);
}
