package com.lemon.server.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.lemon.server.model.Employee;
import com.lemon.server.model.Salary;
import com.lemon.server.pojo.common.RespBean;
import com.lemon.server.pojo.common.RespPageBean;
import com.lemon.server.service.IEmployeeService;
import com.lemon.server.service.ISalaryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 员工账套过来
 *
 * @author: Lemon
 * @Date : 2021/7/2 0:13
 */
@RestController
@RequestMapping("/salary/sobcfg")
public class SalarySobCfgController {

    @Autowired
    private ISalaryService salaryService;
    @Autowired
    private IEmployeeService employeeService;

    @ApiOperation("获取所有工资账套")
    @GetMapping("/salaries")
    public List<Salary> getAllSalaries() {
        return salaryService.list();
    }

    @ApiOperation("获取所有员工账套")
    @GetMapping("/")
    public RespPageBean getEmpWithSalary(@RequestParam(defaultValue = "1") Integer currentPage,
                                         @RequestParam(defaultValue = "10") Integer size) {
        return employeeService.getEmployeeWithSalary(currentPage, size);
    }

    @ApiOperation("更新员工账套")
    @PutMapping("/")
    public RespBean updateEmployeeSalary(Integer eid, Integer sid) {
        if (employeeService.update(new UpdateWrapper<Employee>().set("salaryId", sid).eq("id", eid))) {
            return RespBean.ok("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

}
