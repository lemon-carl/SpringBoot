package com.mmall.service;

import com.mmall.beans.PageQuery;
import com.mmall.beans.PageResult;
import com.mmall.model.SysAcl;
import com.mmall.param.AclParam;

/**
 * @InterFaceName : SysAclService
 * Created with IDEA
 * @author:CarlLing
 * @CreateDate : 2019-01-27 16:52
 * @Description : 权限接口
 */
public interface SysAclService {

    /**
     * 保存权限
     * @param param
     */
    void save(AclParam param);

    /**
     * 更新
     * @param param
     */
    void update(AclParam param);

    /**
     * 根据权限模块获取权限点接口
     * @param aclModuleId
     * @param page
     * @return
     */
    PageResult<SysAcl> getPageByAclModuleId(Integer aclModuleId, PageQuery page);

    /**
     * 获取特定权限
     * @param aclId
     * @return
     */
    //Map getAclByAclId(int aclId);
}
