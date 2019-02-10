package com.mmall.service;

import com.mmall.beans.PageQuery;
import com.mmall.controller.PageResult;
import com.mmall.model.SysUser;
import com.mmall.param.UserParam;

import java.util.List;

/**
 * @InterFaceName : SysUserService
 * Created with IDEA
 * @author:CarlLing
 * @CreateDate : 2019-01-20 21:40
 * @Description :
 */
public interface SysUserService {

    void save(UserParam param);

    void update(UserParam param);

    SysUser findByKeyword(String keyword);

    PageResult<SysUser> getPageByDeptId(int deptId, PageQuery page);

    List<SysUser> getAll();
}
