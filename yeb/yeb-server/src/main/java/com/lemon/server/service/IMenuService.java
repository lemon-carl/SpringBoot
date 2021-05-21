package com.lemon.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lemon.server.pojo.Menu;
import org.springframework.stereotype.Service;

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
}
