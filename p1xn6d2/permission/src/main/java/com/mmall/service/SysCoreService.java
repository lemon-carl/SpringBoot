package com.mmall.service;

import com.mmall.model.SysAcl;

import java.util.List;

/**
 * @Auth CarlLing
 * @Date 2019/1/31 16:49
 * @Version 1.0
 * @Desc
 */
public interface SysCoreService {

    /**
     * 获取当前用户的权限
     * @return
     */
    List<SysAcl> getCurrentUserAclList();

    /**
     *  获取角色的权限
     * @param roleId
     * @return
     */
    List<SysAcl> getRoleAclList(int roleId);

    /**
     *  获取一个用户权限点的综合
     * @param userId
     * @return
     */
    List<SysAcl> getUserAclList(int userId);


    /**
     * 获取 权限
     * @param url
     * @return
     */
    boolean hasUrlAcl(String url);

    /**
     * 判断是否是超级管理员
     * @return
     */
    boolean isSuperAdmin();
}
