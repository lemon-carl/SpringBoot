package com.mmall.apacheshiro.config;

import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * @ClassName : ShiroConfiguration Created with IDEA
 *
 * @author:CarlLing @CreateDate : 2019-01-05 17:30 @Description : Shiro配置文件
 */
@Configuration
public class ShiroConfiguration {

  Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);

  @Bean("shiroFilter")
  public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager) {
    logger.info("-------------shiroFiler-------------------------");
    ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
    bean.setSecurityManager(manager);

    bean.setLoginUrl("/login");
    bean.setSuccessUrl("/index");
    bean.setUnauthorizedUrl("/unauthorized");

    LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
    // 符合条件的URL指定后面的拦截器
    filterChainDefinitionMap.put("/index", "authc");
    filterChainDefinitionMap.put("/login", "anon");
    filterChainDefinitionMap.put("/loginUser", "anon");
    filterChainDefinitionMap.put("/loginUser", "anon");
    filterChainDefinitionMap.put("/admin", "roles[admin]");
    filterChainDefinitionMap.put("/edit", "perms[edit]");
    // 不需要fitler拦截,/druid/**,两个星队druid请求全部放开
    //druid username:druid,pwd :12345678
    filterChainDefinitionMap.put("/druid/**", "anon");
    filterChainDefinitionMap.put("/**", "user");
    bean.setFilterChainDefinitionMap(filterChainDefinitionMap);

    return bean;
  }

  // 配置核心安全事务管理器
  @Bean("securityManager")
  public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm) {
    logger.info("------------------shrio sercurityManager-----------------");
    DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
    manager.setRealm(authRealm);
    return manager;
  }

  // 配置自定义权限登录器
  @Bean("authRealm")
  public AuthRealm authRealm(@Qualifier("credentialMatcher") CredentialMatcher matcher) {
    logger.info("-----------------------authRealm--------------------------");
    AuthRealm authRealm = new AuthRealm();
        authRealm.setCacheManager(new MemoryConstrainedCacheManager());
    authRealm.setCredentialsMatcher(matcher);
    return authRealm;
  }

  @Bean("credentialMatcher")
  public CredentialMatcher credentialMatcher() {
    return new CredentialMatcher();
  }

  @Bean
  public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
      @Qualifier("securityManager") SecurityManager securityManager) {
    AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
    advisor.setSecurityManager(securityManager);
    return advisor;
  }

  @Bean
  public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
    DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
    creator.setProxyTargetClass(true);
    return creator;
  }
}
