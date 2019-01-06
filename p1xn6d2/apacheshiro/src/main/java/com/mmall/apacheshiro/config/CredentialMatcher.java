package com.mmall.apacheshiro.config;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * @ClassName : CredentialMatcher Created with IDEA
 *
 * @author:CarlLing @CreateDate : 2019-01-05 17:23 @Description : 密码的校验规则
 */
public class CredentialMatcher extends SimpleCredentialsMatcher {

  /**
   * 比较密码
   *
   * @param token
   * @param info
   * @return
   */
  @Override
  public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
    UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
    String password = new String(usernamePasswordToken.getPassword());
    String dbPassword = (String) info.getCredentials();
    return this.equals(password, dbPassword);
  }
}
