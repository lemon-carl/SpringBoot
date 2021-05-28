package com.lemon.server.controller;


import com.lemon.server.model.Department;
import com.lemon.server.pojo.common.RespBean;
import com.lemon.server.service.IDepartmentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理（基础信息模块）
 *
 * @author lemon
 * @since 2021-04-07
 */
@RestController
@RequestMapping("/system/basic/department")
public class DepartmentController {
    @Autowired
    private IDepartmentService departmentService;

    @ApiOperation("查询部门")
    @GetMapping("/")
    public List<Department> get() {
        return departmentService.getAllDepartments();
    }

    @ApiOperation("添加部门")
    @PostMapping("/")
    public RespBean add(@RequestBody Department department) {
        return departmentService.addDep(department);
    }

    @ApiOperation("删除部门")
    @DeleteMapping("/{id}")
    public RespBean deleteById(@PathVariable("id") Integer id) {
        return departmentService.deleteById(id);
    }
}
