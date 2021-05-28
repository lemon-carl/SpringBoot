package com.lemon.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lemon.server.model.Department;
import com.lemon.server.pojo.common.RespBean;

import java.util.List;

/**
 *  部门业务层
 *
 * @author lemon
 * @since 2021-04-07
 */
public interface IDepartmentService extends IService<Department> {

    /**
     * 查询部门
     *
     * @return
     */
    List<Department> getAllDepartments();

    /**
     * 添加部门
     *
     * @param department
     * @return
     */
    RespBean addDep(Department department);

    /**
     * 删除部门
     *
     * @param id
     * @return
     */
    RespBean deleteById(Integer id);
}
