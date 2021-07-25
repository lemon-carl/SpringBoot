package com.lemon.server.controller.user;

import com.lemon.server.model.Admin;
import com.lemon.server.pojo.common.RespBean;
import com.lemon.server.pojo.vo.AdminLoginVO;
import com.lemon.server.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 *  登录模块
 *
 * @author: Lemon
 * @Date : 2021/4/11 19:05
 */
@Api(tags = "LoginController")
@RestController
public class LoginController {

    @Autowired
    private IAdminService adminService;

    @ApiOperation(value = "登录之后返回token")
    @PostMapping("/login")
    public RespBean login(@RequestBody AdminLoginVO adminLoginVO, HttpServletRequest request) {
        return adminService.login(adminLoginVO.getUsername(), adminLoginVO.getPassword(), adminLoginVO.getCode(), request);
    }

    @ApiOperation(value = "获取当前登录的用户信息")
    @GetMapping("/admin/info")
    public Admin getAdminInfo(Principal principal) {
        if (null == principal) {
            return null;
        }
        String username = principal.getName();
        Admin admin = adminService.getAdminByUserName(username);
        admin.setPassword(null);
        admin.setRoles(adminService.getRoles(admin.getId()));
        return admin;
    }

    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public RespBean logout() {
        return RespBean.ok("注销成功！");
    }
}
