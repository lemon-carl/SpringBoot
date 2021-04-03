package com.lemon.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Date : 2021/4/4 0:46
 * @author: CarlLing
 * @Version 1.0
 * @Desc : 
 */
@SpringBootApplication
@MapperScan("com.lemon.server.mapper")
public class YebAppliction {

    public static void main(String[] args) {
        SpringApplication.run(YebAppliction.class,args);
    }
}
