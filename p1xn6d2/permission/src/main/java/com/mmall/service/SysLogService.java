package com.mmall.service;

import com.mmall.beans.PageQuery;
import com.mmall.beans.PageResult;
import com.mmall.model.*;
import com.mmall.param.SearchLogParam;

/**
 * @InterFaceName : SysLogService
 * Created with IDEA
 * @author:CarlLing
 * @CreateDate : 2019-02-12 23:17
 * @Description :
 */
public interface SysLogService {

    /**
     * 保存部门日志
     * @param before
     * @param after
     */
    void saveDeptLog(SysDept before, SysDept after);

    /**
     * 保存用户记录
     * @param before
     * @param after
     */
    void saveUserLog(SysUser before, SysUser after);

    /**
     * 权限模块
     * @param before
     * @param after
     */
    void saveAclModuleLog(SysAclModule before, SysAclModule after);

    /**
     * 权限记录
     * @param before
     * @param after
     */
    void saveAclLog(SysAcl before, SysAcl after);

    /**
     * 角色模块
     * @param before
     * @param after
     */
    void saveRoleLog(SysRole before, SysRole after);

    /**
     * 角色权限日志
     * @param roleId
     * @param before
     * @param after
     */
    //void saveRoleAclLog(int roleId,  List<Integer> before, List<Integer> after);

    /**
     * 用户角色日志
     * @param roleId
     * @param before
     * @param after
     */
    //void saveRoleUserLog(int roleId, List<Integer> before, List<Integer> after);

    /**
     * 记录恢复
     * @param id
     */
    void recover(int id);

    /**
     * 查询记录列表
     * @param param
     * @param page
     * @return
     */
    PageResult<SysLogWithBLOBs> searchPageList(SearchLogParam param, PageQuery page);


}
