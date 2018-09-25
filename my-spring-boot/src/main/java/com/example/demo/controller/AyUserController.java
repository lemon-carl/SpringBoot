package com.example.demo.controller;

import com.example.demo.model.AyUser;
import com.example.demo.service.AyUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Carl
 * @Date: 2018/9/18 13:05
 * @Version 1.0
 */
@Controller
@RequestMapping("/ayUser")
public class AyUserController {

    @Resource
    private AyUserService ayUserService;

    @RequestMapping("/test")
    public String test(Model model){
       //
        List<AyUser> ayUsers = ayUserService.findAll();
        model.addAttribute("users",ayUsers);

        return "ayUser";
    }
}
