package com.lemon.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lemon.server.model.Menu;

import java.util.List;

/**
 * 菜单  Mapper 接口
 *
 * @author lemon
 * @since 2021-04-07
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据用户id查询菜单列表
     * @param id
     * @return
     */
    List<Menu> selectMenusByAdminId(Integer id);

    /**
     * 根据角色获取菜单列表
     * @return
     */
    List<Menu> getMenusWithRole();
}
