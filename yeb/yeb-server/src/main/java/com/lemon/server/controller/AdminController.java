package com.lemon.server.controller;


import com.lemon.server.model.Admin;
import com.lemon.server.model.Role;
import com.lemon.server.pojo.common.RespBean;
import com.lemon.server.service.IAdminService;
import com.lemon.server.service.IRoleService;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 操作员管理(系统管理)
 *
 * @author lemon
 * @since 2021-04-07
 */
@RestController
@RequestMapping("/system/admin")
public class AdminController {
    @Autowired
    private IAdminService adminService;
    @Autowired
    private IRoleService roleService;


    @ApiOperation("查询所有操作员")
    @GetMapping("/")
    public List<Admin> getAdmin(@RequestParam(value = "keyWorks", required = false) String keyWorks) {
        return adminService.getAllAdmins(keyWorks);
    }

    // TODO:新增操作员功能待实现......

    @ApiOperation("更新操作员")
    @PutMapping("/")
    public RespBean updateAdmin(@RequestBody Admin admin) {
        if (adminService.updateById(admin)) {
            return RespBean.ok("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation("删除操作员")
    @DeleteMapping("/{id}")
    public RespBean deleteAdmin(@PathVariable("id") Integer id) {
        if (adminService.removeById(id)) {
            return RespBean.ok("删除成功！");
        }
        return RespBean.error("删除失败！");
    }

    @ApiOperation("获取操作管理所有角色")
    @GetMapping("/roles")
    public List<Role> getAllRole() {
        return roleService.list();
    }

    @ApiOperation("更新操作员角色权限")
    @PutMapping("/role")
    public RespBean updateAdminRole(Integer adminId, Integer[] rids) {
        return adminService.updateAdminRole(adminId, rids);
    }

}
