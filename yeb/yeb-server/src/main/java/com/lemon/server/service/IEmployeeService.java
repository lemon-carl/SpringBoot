package com.lemon.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lemon.server.model.Employee;
import com.lemon.server.pojo.common.RespBean;
import com.lemon.server.pojo.common.RespPageBean;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工业务接口
 *
 * @author lemon
 * @since 2021-04-07
 */
public interface IEmployeeService extends IService<Employee> {

    /**
     * 获取所有员工（分页）
     * @param currentPage
     * @param size
     * @param employee
     * @param beginDateScope
     * @return
     */
    RespPageBean getEmployeePage(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope);

    /**
     * 获取工号
     *
     * @return
     */
    RespBean maxWorkID();

    /**
     * 添加员工
     *
     * @param employee
     * @return
     */
    RespBean addEmp(Employee employee);

    /**
     * 查询员工
     *
     * @param id
     * @return
     */
    List<Employee> getEmployee(Integer id);

    /**
     * 获取所有员工账套
     *
     * @param currentPage
     * @param size
     * @return
     */
    RespPageBean getEmployeeWithSalary(Integer currentPage, Integer size);
}
