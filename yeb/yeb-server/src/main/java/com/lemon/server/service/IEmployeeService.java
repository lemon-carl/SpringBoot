package com.lemon.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lemon.server.model.Employee;
import com.lemon.server.pojo.common.RespPageBean;

import java.time.LocalDate;

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
}
