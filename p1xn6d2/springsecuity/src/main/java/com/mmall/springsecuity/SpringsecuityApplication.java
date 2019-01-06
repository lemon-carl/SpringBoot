package com.mmall.springsecuity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringsecuityApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringsecuityApplication.class, args);
  }

  @RequestMapping("/")
  public String home(){
    return "hello spring boot";
  }

  @RequestMapping("/hello")
  public String hello(){
    return "hello world";
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @RequestMapping("/roleAuth")
  public String role(){
    return "admin auth";
  }

  @PreAuthorize("#id<10 and principal.username.equals(#username) and #user.username.equals('abc')")
  @PostAuthorize("returnObject%2==0")
  @RequestMapping("/test")
  public String test1(Integer id, String username, User user){
    return "test auth";
  }


  @PreFilter("filterObject%2==0")
  @PostFilter("filterObject%4==0")
  @RequestMapping("/test2")
  public List<Integer> test2(List<Integer> idList){
     //...
    return idList;
  }



}


