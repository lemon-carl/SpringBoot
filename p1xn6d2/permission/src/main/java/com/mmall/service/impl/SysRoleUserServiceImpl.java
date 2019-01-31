package com.mmall.service.impl;

import com.mmall.model.SysUser;
import com.mmall.service.SysRoleUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName : SysRoleUserServiceImpl
 * Created with IDEA
 * @author:CarlLing
 * @CreateDate : 2019-01-31 23:59
 * @Description :
 */
@Service
public class SysRoleUserServiceImpl implements SysRoleUserService {


    @Override
    public void changeRoleUsers(int roleId, List<Integer> userIdList) {

    }

    @Override
    public List <SysUser> getListByRoleId(int roleId) {
        return null;
    }
}
