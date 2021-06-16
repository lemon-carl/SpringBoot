package com.lemon.server.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Mybatis 分页配置
 * @author: Lemon
 * @Date : 2021/6/12 17:36
 * 
 */
@Configuration
public class MyBatisPlusConfig {

    /**
     * PaginationInterceptor 该类过时 , 分页返回 total 为0 需添加
     *   interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
     * @return
     */
    @Bean
    public MybatisPlusInterceptor paginationInterceptor(){
        MybatisPlusInterceptor interceptor =  new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
