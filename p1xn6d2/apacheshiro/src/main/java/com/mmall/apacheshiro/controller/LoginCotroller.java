package com.mmall.apacheshiro.controller;

import com.mmall.apacheshiro.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @ClassName : LoginCotroller Created with IDEA
 *
 * @author:CarlLing @CreateDate : 2019-01-05 18:06 @Description : 接口测试
 */
@Controller
public class LoginCotroller {

  Logger logger = LoggerFactory.getLogger(LoginCotroller.class);

  @RequestMapping("/login")
  public String login() {
    logger.info("login....");
    return "login";
  }

  @RequestMapping("/index")
  public String index() {
    logger.info("index.....");
    return "index";
  }

  @RequestMapping("/logout")
  public String logout() {
    logger.info("logout.....");
    Subject subject = SecurityUtils.getSubject();
    if(subject != null){
      subject.logout();
    }
    return "login";
  }

  @RequestMapping("unauthorized")
  public String unauthorized() {
    return "unauthorized";
  }

  @RequestMapping("/admin")
  @ResponseBody
  public String admin() {
    logger.info("admin.....");
    return "admin success";
  }

  @RequestMapping("/edit")
  @ResponseBody
  public String edit() {
    logger.info("edit.....");
    return "edit success";
  }

  @RequestMapping("/loginUser")
  public String loginUser(
      @RequestParam("username") String username,
      @RequestParam("password") String password,
      HttpSession session) {

    logger.info("loginUser.....");
    UsernamePasswordToken token = new UsernamePasswordToken(username, password);
    Subject subject = SecurityUtils.getSubject();
    try {
      subject.login(token);
      User user = (User) subject.getPrincipal();
      session.setAttribute("user", user);
      return "index";

    } catch (Exception e) {
      return "login";
    }
  }
}
