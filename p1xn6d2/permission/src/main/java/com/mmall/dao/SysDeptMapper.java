package com.mmall.dao;

import com.mmall.model.SysDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysDeptMapper {
    int deleteByPrimaryKey(@Param("id")Integer id);

    int insert(SysDept record);

    int insertSelective(SysDept record);

    SysDept selectByPrimaryKey(@Param("id")Integer id);

    int updateByPrimaryKeySelective(SysDept record);

    int updateByPrimaryKey(SysDept record);

    // 部门列表
    List<SysDept> getAllDept();

    //获取所有以x开头的所有部门 xx .%
    List<SysDept> getChildDeptListByLevel(@Param("level") String level);

    //批量更新所有的子部门
    void  batchUpdateLevel(@Param("sysDeptList") List<SysDept> sysDeptList);

    //检查数据是否有重复
    int countByNameAndParentId(@Param("parentId") Integer parentId, @Param("name") String name, @Param("id") Integer id);

    int countByParentId(@Param("deptId") Integer deptId);

}