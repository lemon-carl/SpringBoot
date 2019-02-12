package com.mmall.service;

import com.mmall.dto.AclModuleLevelDto;
import com.mmall.dto.DeptLevelDto;

import java.util.List;

/**
 * 树形结构遍历接口
 */
public interface SysTreeService {

    /**
     * 部门树
     * @return
     */
    List<DeptLevelDto> deptTree();

    /**
     * 权限模块
     * @return
     */
    List<AclModuleLevelDto> aclModuleTree();

    /**
     * 用户权限
     * @param userId
     * @return
     */
    List<AclModuleLevelDto> userAclTree(int userId);

    /**
     * 角色树
     * @param roleId
     * @return
     */
    List<AclModuleLevelDto> roleTree(int roleId);
}
