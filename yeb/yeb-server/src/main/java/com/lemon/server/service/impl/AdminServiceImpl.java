package com.lemon.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lemon.server.mapper.AdminRoleMapper;
import com.lemon.server.mapper.MenuMapper;
import com.lemon.server.mapper.RoleMapper;
import com.lemon.server.model.*;
import com.lemon.server.utils.AdminUtils;
import com.lemon.server.utils.JwtTokenUtil;
import com.lemon.server.mapper.AdminMapper;
import com.lemon.server.pojo.common.RespBean;
import com.lemon.server.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户业务实现类
 *
 * @author lemon
 * @since 2021-04-07
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {
    @Autowired
    private RedisTemplate redisTemplate;
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
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private AdminRoleMapper adminRoleMapper;
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public RespBean login(String username, String password, String code, HttpServletRequest request) {
        String captcha = (String) request.getSession().getAttribute("captcha");
        if (!StringUtils.hasLength(code) || !captcha.equalsIgnoreCase(code)) {
            return RespBean.error("验证码输入错误,请重新输入！");
        }

        // 登录
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (null == userDetails || !passwordEncoder.matches(password, userDetails.getPassword())) {
            return RespBean.error("用户名或密码不正确");
        }

        if (!userDetails.isEnabled()) {
            return RespBean.error("账号被禁用,请联系管理员！");
        }

        // 更新 security 登录用户对象,参数1.参数2- 密码（一般不放）,参数3，权限列表
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        // security 全局里
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        // 生成token
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String, String> tokenMap = new HashMap<>(16);
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return RespBean.ok("登录成功", tokenMap);
    }

    @Override
    public Admin getAdminByUserName(String username) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<Admin>()
                .eq("username", username)
                .eq("enabled", true);
        return adminMapper.selectOne(queryWrapper);
    }

    @Override
    public List<Role> getRoles(Integer adminId) {
        return roleMapper.getRoles(adminId);
    }

    @Override
    public List<Admin> getAllAdmins(String keyWorks) {
        return adminMapper.getAllAdmins(AdminUtils.getCurrentAdmin().getId(), keyWorks);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RespBean updateAdminRole(Integer adminId, Integer[] rids) {
        QueryWrapper<AdminRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("adminId", adminId);
        adminRoleMapper.delete(queryWrapper);

        Integer result = adminRoleMapper.updateAdminRole(adminId, rids);
        if (rids.length == result) {
            ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
            // 从redis中获取菜单数据
            List<Menu> menus = (List<Menu>) valueOperations.get("menu_" + adminId);
            // 如果不为空，则删除之前的值,从数据库中获取
            if (CollectionUtils.isNotEmpty(menus)) {
                redisTemplate.delete("menu_"+ adminId);
                menus = menuMapper.selectMenusByAdminId(adminId);
                // 将数据设置到redis中
                valueOperations.set("menu_" + adminId, menus);
            } else {
                menus = menuMapper.selectMenusByAdminId(adminId);
                // 将数据设置到redis中
                valueOperations.set("menu_" + adminId, menus);
            }

            return RespBean.ok("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

    @Override
    public RespBean updateAdminPassword(String oldPass, String pass, Integer adminId) {
        Admin admin = adminMapper.selectById(adminId);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // 比较老密码是否与数据库加密的一致
        if (encoder.matches(oldPass, admin.getPassword())) {
            // 新密码加密并设置
            admin.setPassword(encoder.encode(pass));
            int result = adminMapper.updateById(admin);
            if (1 == result) {
                return RespBean.ok("更新密码成功！");
            }
        }
        return RespBean.error("更新密码失败！");
    }
}
