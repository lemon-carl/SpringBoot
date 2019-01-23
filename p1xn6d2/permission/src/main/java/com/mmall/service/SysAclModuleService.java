package com.mmall.service;

import com.mmall.param.AclModuleParam;
import com.mmall.param.DeptParam;

/**
 * @InterFaceName : SysAclModuleServiceImpl
 * Created with IDEA
 * @author:CarlLing
 * @CreateDate : 2019-01-23 23:03
 * @Description :
 */
public interface SysAclModuleService {

    /**
     * 保存权限模块
     * @param param 权限模块参数
     */
    void save(AclModuleParam param);

    /**
     * 更新权限模块
     * @param param
     */
    void update(AclModuleParam param);

    /**
     * 删除权限模块
     * @param id
     */
    void delete(int id);
}
