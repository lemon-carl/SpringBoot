package com.mmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName : AdminController
 * Created with IDEA
 * @author:CarlLing
 * @CreateDate : 2019-01-20 22:37
 * @Description :  登录成功跳转
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("/index.page")
    public ModelAndView index(){
       return  new ModelAndView("admin");
    }
}
