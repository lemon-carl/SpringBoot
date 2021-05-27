package com.lemon.server.controller;


import com.lemon.server.model.Menu;
import com.lemon.server.service.IMenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 系统管理(菜单)
 *
 * @author lemon
 * @since 2021-04-07
 */
@RestController
@RequestMapping("/system/config")
public class MenuController {
    @Autowired
    private IMenuService menuService;

    @ApiOperation("根据用户id查询菜单列表")
    @GetMapping("/menu")
    public List<Menu> getMenusByAdminId() {
        return menuService.getMenusByAdminId();
    }
}
