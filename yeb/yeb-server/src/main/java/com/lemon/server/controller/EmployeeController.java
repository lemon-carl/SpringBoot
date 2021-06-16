package com.lemon.server.controller;


import com.lemon.server.model.Employee;
import com.lemon.server.pojo.common.RespPageBean;
import com.lemon.server.service.IEmployeeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * 员工资料(基本资料)
 *
 * @author lemon
 * @since 2021-04-07
 */
@RestController
@RequestMapping("/employee/basic")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @ApiOperation("获取所有员工")
    @GetMapping("/")
    public RespPageBean getEmployee(@RequestParam(defaultValue = "1") Integer currentPage,
                                    @RequestParam(defaultValue = "10") Integer size,
                                    Employee employee, LocalDate[] beginDateScope){

        return employeeService.getEmployeePage(currentPage,size,employee,beginDateScope);
    }
}
