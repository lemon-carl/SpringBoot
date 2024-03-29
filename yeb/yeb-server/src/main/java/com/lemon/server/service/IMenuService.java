package com.lemon.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lemon.server.model.Menu;
import com.lemon.server.pojo.vo.MenuVO;

import java.util.List;

/**
 * 菜单业务
 *
 * @author lemon
 * @since 2021-04-07
 */
public interface IMenuService extends IService<Menu> {

    /**
     * 根据用户id查询菜单列表
     * @return
     */
    List<Menu> getMenusByAdminId();

    /**
     * 根据角色获取菜单列表
     * @return
     */
    List<Menu> getMenusWithRole();

    /**
     * 查询所有菜单
     *
     * @return
     */
    List<MenuVO> getAllMenus();
}
