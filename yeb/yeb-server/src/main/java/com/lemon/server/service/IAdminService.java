package com.lemon.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lemon.server.model.Admin;
import com.lemon.server.model.Role;
import com.lemon.server.pojo.common.RespBean;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *
 * 用户业务接口
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
     * @param code
     * @param request  请求
     * @return
     */
    RespBean login(String username, String password, String code, HttpServletRequest request);

    /**
     * 根据用户名获取用户
     *
     * @param username
     * @return
     */
    Admin getAdminByUserName(String username);

    /**
     * 根据用户Id查询角色列表
     * @param adminId
     * @return
     */
    List<Role> getRoles(Integer adminId);

    /**
     * 查询所有操作员
     *
     * @param keyWorks
     * @return
     */
    List<Admin> getAllAdmins(String keyWorks);

    /**
     * 更新操作员角色
     *
     * @param adminId
     * @param rids
     * @return
     */
    RespBean updateAdminRole(Integer adminId, Integer[] rids);

    /**
     * 更新用户密码
     *
     * @param oldPass
     * @param pass
     * @param adminId
     * @return
     */
    RespBean updateAdminPassword(String oldPass, String pass, Integer adminId);
}
