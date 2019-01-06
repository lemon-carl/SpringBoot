package com.mmall.apacheshiro.model;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName : User Created with IDEA
 *
 * @author:CarlLing @CreateDate : 2019-01-05 15:32 @Description : 用户表
 */
public class User {

  private Integer uid;

  private String username;

  private String password;

  private Set<Role> roles = new HashSet<>();

  public Integer getUid() {
    return uid;
  }

  public void setUid(Integer uid) {
    this.uid = uid;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }
}
