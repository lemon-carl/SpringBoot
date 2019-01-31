package com.mmall.service;

import java.util.List;

/**
 * @InterFaceName : SysRoleAclService
 * Created with IDEA
 * @author:CarlLing
 * @CreateDate : 2019-01-31 23:55
 * @Description :   用户权限映射接口
 */
public interface SysRoleAclService {

    void changeRoleAcls(int roleId, List<Integer> aclIdList);

}
