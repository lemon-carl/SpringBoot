package com.lemon.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lemon.server.pojo.Admin;
import com.lemon.server.pojo.common.RespBean;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lemon
 * @since 2021-04-07
 */
public interface IAdminService extends IService<Admin> {

    /**
     * 登录之后返户token
     *
     * @param username 用户名
     * @param password 密码
     * @param request  请求
     * @return
     */
    RespBean login(String username, String password, HttpServletRequest request);

    /**
     * 根据用户名获取用户
     *
     * @param username
     * @return
     */
    Admin getAdminByUserName(String username);
}
