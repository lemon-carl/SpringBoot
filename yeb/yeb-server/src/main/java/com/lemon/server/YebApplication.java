package com.lemon.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Date : 2021/4/4 0:46
 * @author: CarlLing
 * @Version 1.0
 * @Desc : 
 */
@SpringBootApplication( exclude = { RedisRepositoriesAutoConfiguration.class })
@MapperScan("com.lemon.server.mapper")
@EnableScheduling
public class YebApplication {

    public static void main(String[] args) {
        SpringApplication.run(YebApplication.class, args);
    }
}
