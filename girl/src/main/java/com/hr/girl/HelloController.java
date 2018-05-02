package com.hr.girl;/**
 * packageName :com.hr.girl
 * Created with IDEA
 * author:CarlLing
 * Date:2018/5/2
 * Time:18:01
 */

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Name : HelloController
 * @Author :  LH
 * @CreateDate : 2018-05-02 18:01
 * @Description : 
 */
@RestController
public class HelloController {

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String say(){
        return "Hello Spring Boot!";
    }
}
