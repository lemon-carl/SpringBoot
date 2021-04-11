package com.lemon.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lemon.server.config.security.JwtTokenUtil;
import com.lemon.server.mapper.AdminMapper;
import com.lemon.server.pojo.Admin;
import com.lemon.server.pojo.common.RespBean;
import com.lemon.server.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.http.SecurityHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lemon
 * @since 2021-04-07
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;


    /**
     * 登录之后返户token
     *
     * @param username 用户名
     * @param password 密码
     * @param request  请求
     * @return
     */
    @Override
    public RespBean login(String username, String password, HttpServletRequest request) {
        // 登录
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (null == userDetails || !passwordEncoder.matches(password, userDetails.getPassword())) {
            return RespBean.error("用户名或密码不正确");
        }

        if (!userDetails.isEnabled()) {
            return RespBean.error("账号被禁用,请联系管理员！");
        }

        // 更新 security 登录用户对象,参数1.参数2- 密码（一般不放）,参数3，权限列表
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        // security 全局里
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        // 生成token
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHaded", tokenHead);
        return RespBean.success("登录成功", tokenMap);
    }

    /**
     * 根据用户名获取用户
     *
     * @param username
     * @return
     */
    @Override
    public Admin getAdminByUserName(String username) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<Admin>().eq("username", username).eq("enabled", true);
        return adminMapper.selectOne(queryWrapper);
    }
}
