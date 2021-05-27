package com.lemon.server.controller;


import com.lemon.server.model.Role;
import com.lemon.server.pojo.common.RespBean;
import com.lemon.server.service.IRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色模块
 *
 * @author lemon
 * @since 2021-04-07
 */
@RestController
@RequestMapping("/system/basic/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @ApiOperation("获取所有角色")
    @GetMapping("/")
    public List<Role> get() {
        return roleService.list();
    }

    @ApiOperation("添加角色")
    @PostMapping("/")
    public RespBean add(@RequestBody Role role) {
        if (roleService.save(role)) {
            return RespBean.ok("添加成功！");

        }
        return RespBean.error("添加失败！");
    }

    @ApiOperation("更新角色")
    @PutMapping("/")
    public RespBean update(@RequestBody Role role) {
        if (roleService.updateById(role)) {
            return RespBean.ok("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation("删除角色")
    @DeleteMapping("/{id}")
    public RespBean delete(@PathVariable("id") Integer id) {
        if (roleService.removeById(id)) {
            return RespBean.ok("删除成功！");
        }
        return RespBean.error("删除失败！");
    }


}
