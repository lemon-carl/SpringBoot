package com.lemon.server.controller;


import com.lemon.server.model.Salary;
import com.lemon.server.pojo.common.RespBean;
import com.lemon.server.service.ISalaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 工资账套管理
 *
 * @author lemon
 * @since 2021-04-07
 */
@RestController
@RequestMapping("/salary/sob")
public class SalaryController {

    @Autowired
    private ISalaryService salaryService;

    @ApiOperation(value = "获取所有工资账套")
    @GetMapping("/")
    public List<Salary> getAllSalary() {
        return salaryService.list();
    }

    @ApiOperation(value = "添加工资账套")
    @PostMapping("/")
    public RespBean addSalary(@RequestBody Salary salary) {
        salary.setCreateDate(LocalDateTime.now());
        if (salaryService.save(salary)) {
            return RespBean.ok("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @ApiOperation("删除工资账套")
    @DeleteMapping("/{id}")
    public RespBean deleteSalary(@PathVariable("id") Integer id) {
        if (salaryService.removeById(id)) {
            return RespBean.ok("删除成功！");
        }
        return RespBean.error("删除失败！");
    }

    @ApiOperation("更新工资账套")
    @PutMapping("/")
    public RespBean updateSalary(@RequestBody Salary salary) {
        if (salaryService.updateById(salary)) {
            return RespBean.ok("更新成功！");
        }
        return RespBean.error("更新失败！");
    }


}
