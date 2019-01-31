package com.mmall.service;

import com.mmall.model.SysUser;

import java.util.List;

/**
 * @InterFaceName : SysRoleUserService
 * Created with IDEA
 * @author:CarlLing
 * @CreateDate : 2019-01-31 23:55
 * @Description :   用户角色映射接口
 */
public interface SysRoleUserService {

    /**
     * 改变角色用户列表
     * @param roleId
     * @param userIdList
     */
    void changeRoleUsers(int roleId, List<Integer> userIdList);

    /**
     * 获取角色用具列表
     * @param roleId
     * @return
     */
    List<SysUser> getListByRoleId(int roleId);
}
