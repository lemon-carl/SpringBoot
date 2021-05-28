package com.lemon.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lemon.server.constants.DepartmentConstants;
import com.lemon.server.mapper.DepartmentMapper;
import com.lemon.server.model.Department;
import com.lemon.server.pojo.common.RespBean;
import com.lemon.server.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 部门业务实现类
 *
 * @author lemon
 * @since 2021-04-07
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> getAllDepartments() {
        return departmentMapper.getAllDepartmentsById(-1);
    }

    @Override
    public RespBean addDep(Department department) {
        department.setEnabled(true);
        departmentMapper.insertDep(department);
        if (1 == department.getResult()) {
            return RespBean.ok("添加成功！", department);
        }
        return RespBean.error("添加失败！");
    }

    @Override
    public RespBean deleteById(Integer id) {
        Department dep = new Department();
        dep.setId(id);
        departmentMapper.deleteDepById(dep);
        if (dep.getResult().equals(Integer.parseInt(DepartmentConstants.DEP_CONTAIN_SUB_DEP))) {
            return RespBean.error("该部门下还有子部门,删除失败！");
        }
        if (dep.getResult().equals(Integer.parseInt(DepartmentConstants.DEP_CONTAIN_EMPLOYEE))) {
            return RespBean.error("该部门下还有员工,删除失败！");
        }
        if (dep.getResult().equals(Integer.parseInt(DepartmentConstants.DEP_DELETE_SUCESS_RESULT))) {
            return RespBean.ok("删除成功！");
        }

        return RespBean.error("删除失败！");
    }
}
