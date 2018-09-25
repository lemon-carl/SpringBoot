package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/** @Author: Carl @Date: 2018/9/14 14:05 @Version 1.0 */
@Entity
@Table(name="ay_user")
public class AyUser {

  // 主键
  @Id
  private String id;
  // 用户名
  private String name;
  // 密码
  private String password;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

    @Override
    public String toString() {
        return "AyUser{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
