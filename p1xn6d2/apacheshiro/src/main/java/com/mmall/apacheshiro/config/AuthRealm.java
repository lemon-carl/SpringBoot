package com.mmall.apacheshiro.config;

import com.mmall.apacheshiro.model.Permission;
import com.mmall.apacheshiro.model.Role;
import com.mmall.apacheshiro.model.User;
import com.mmall.apacheshiro.service.UserService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @ClassName : AuthRealm Created with IDEA
 *
 * @author:CarlLing @CreateDate : 2019-01-05 16:54 @Description : 认证授权
 */
public class AuthRealm extends AuthorizingRealm {

  @Autowired
  private UserService userService;

  // 授权
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    User user = (User) principals.fromRealm(this.getClass().getName()).iterator().next();
    List<String> permissionList = new ArrayList<>();
    List<String> roleNameList = new ArrayList<>();
    Set<Role> roleSet = user.getRoles();
    if (CollectionUtils.isNotEmpty(roleSet)) {
      for (Role role : roleSet) {
        roleNameList.add(role.getRname());
        Set<Permission> permissionSet = role.getPermissions();
        if (CollectionUtils.isNotEmpty(permissionSet)) {
          for (Permission permission : permissionSet) {
            permissionList.add(permission.getName());
          }
        }
      }
    }

    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
    info.addStringPermissions(permissionList);
    info.addRoles(roleNameList);
    return info;
  }

  // 认证
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
      throws AuthenticationException {
    UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
    String username = usernamePasswordToken.getUsername();
    User user = userService.findByUsername(username);
    return new SimpleAuthenticationInfo(user, user.getPassword(), this.getClass().getName());
  }
}
