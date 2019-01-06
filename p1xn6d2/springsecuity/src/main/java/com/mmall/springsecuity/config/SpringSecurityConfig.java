package com.mmall.springsecuity.config;

import com.mmall.springsecuity.service.MyUserService;
import com.mmall.springsecuity.utils.MyPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @ClassName : SpringSecurityConfig
 * Created with IDEA
 * @author:CarlLing
 * @CreateDate : 2019-01-01 12:34
 * @Description : SpringSecurity 配置文件
 */

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

  @Autowired private MyUserService myUserService;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // inMemoryAuthentication 从内存中获取
    // auth.inMemoryAuthentication().withUser("admin").password("123456").roles("ADMIN");
    // auth.inMemoryAuthentication().withUser("zhangsan").password("zhang").roles("ADMIN");
    //Spring security 5.0中新增了多种加密方式，也改变了密码的格式。
  /*  auth.inMemoryAuthentication()
        .passwordEncoder(new BCryptPasswordEncoder())
        .withUser("admin")
        .password(new BCryptPasswordEncoder().encode("123"))
        .roles("ADMIN");
    auth.inMemoryAuthentication()
        .passwordEncoder(new BCryptPasswordEncoder())
        .withUser("zhangsan")
        .password(new BCryptPasswordEncoder().encode("zhangsan"))
        .roles("ADMIN");
    auth.inMemoryAuthentication()
        .passwordEncoder(new BCryptPasswordEncoder())
        .withUser("demo")
        .password(new BCryptPasswordEncoder().encode("demo"))
        .roles("USER");*/

    auth.userDetailsService(myUserService).passwordEncoder(new MyPasswordEncoder());
    auth.jdbcAuthentication().usersByUsernameQuery("").authoritiesByUsernameQuery("").passwordEncoder(new MyPasswordEncoder());


  }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        //主路径允许访问，其他请求需要验证，注销允许访问，允许表单登录
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout().permitAll()
                .and()
                .formLogin();
        //默认关闭csrf认证
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //super.configure(web);
        web.ignoring().antMatchers("/js/**","/css/**","/images/**");
    }
}
