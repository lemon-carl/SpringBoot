package com.hr.girl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @ClassName : HelloController
 *  Created with IDEA
 * @Author :  CarlLing
 * @CreateDate : 2018-05-02 18:01
 * @Description : 
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

/*    @Value("${cupSize}")
    private String cupSize;

    @Value("${age}")
    private Integer age;

    @Value("${content}")
    private String content;*/

    @Autowired
    private GirlProperties girlProperties;

   // @RequestMapping(value = "/say",method = RequestMethod.GET)
    @GetMapping(value="/say")
    public String say(@RequestParam(value="id",required = false,defaultValue = "0") Integer myid){
      return  "id:" + myid;
      //  return girlProperties.getCupSize();
     // return "index";
    }
}
