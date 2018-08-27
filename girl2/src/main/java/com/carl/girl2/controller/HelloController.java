package com.carl.girl2.controller;

import com.carl.girl2.properties.GirlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName : HelloController
 * Created with IDEA
 * @author:CarlLing
 * @CreateDate : 2018-08-16 21:43
 * @Description :
 */
@RestController
@RequestMapping(value = "/hello")
public class HelloController {

    @Autowired
    private GirlProperties girlProperties;

    //@RequestMapping(value = "/say",method = RequestMethod.GET)
    @GetMapping(value = "/say")
    public String say(@RequestParam(value = "id", defaultValue = "0", required = false) Integer myId) {
        //   return "Hello Spring Boot !";
        //return girlProperties.getCupSize() + " : "+ girlProperties.getAge();
        return "id=" + myId;
    }
}
