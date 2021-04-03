package com.lemon.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Date : 2021/4/4 0:54
 * @author: CarlLing
 * @Version 1.0
 * @Desc : 
 */
@RestController
@RequestMapping("/test")
public class YebHelloController {

    @GetMapping("/test")
    public String test(){
        return "hello test yeb";
    }
}
