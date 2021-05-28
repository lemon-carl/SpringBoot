package com.lemon.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lemon.server.model.Department;

import java.util.List;

/**
 *
 *  部门 Mapper 接口
 *
 * @author lemon
 * @since 2021-04-07
 */
public interface DepartmentMapper extends BaseMapper<Department> {


    /**
     * 查询部门
     *
     * @param parentId
     * @return
     */
    List<Department> getAllDepartmentsById(Integer parentId);

    /**
     * 添加部门
     *
     * @param department
     */
    void insertDep(Department department);

    /**
     * 删除部门
     *
     * @param dep
     */
    void deleteDepById(Department dep);
}
