package com.mmall.apacheshiro.model;

/**
 * @ClassName : Permission Created with IDEA
 *
 * @author:CarlLing @CreateDate : 2019-01-05 15:33 @Description : 权限表
 */
public class Permission {

  private Integer pid;

  private String name;

  private String url;

  public Integer getPid() {
    return pid;
  }

  public void setPid(Integer pid) {
    this.pid = pid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
