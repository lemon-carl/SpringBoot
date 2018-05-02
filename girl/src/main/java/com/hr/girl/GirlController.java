package com.hr.girl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName : GirlController
 * Created with IDEA
 * @author:CarlLing
 * @CreateDate : 2018-05-02 23:19
 * @Description :
 */
@RestController
public class GirlController {

    @Autowired
    private  GirlRepository girlRepository;

    /**
     * 查询所有女生列表
     * @return
     */
    @GetMapping(value = "/girls")
    public List<Girl> girlList(){
            return girlRepository.findAll();
    }

    /**
     * 添加一个女生
     * @param age
     * @param cupSize
     * @return
     */
    @PostMapping(value = "/girls")
    public Girl girlAdd(@RequestParam("age") Integer age,
                          @RequestParam("cupSize") String cupSize){

          Girl girl = new Girl();
          girl.setAge(age);
          girl.setCupSize(cupSize);

          return  girlRepository.save(girl);
    }

    /**
     * 查询一个女生
     * @param id
     * @return
     */
    @GetMapping(value = "/girls/{id}")
    public Girl findOne(@PathVariable("id") Integer id){
             return  girlRepository.findOne(id);
    }

    /**
     * 更新一个女生
     * @param age
     * @param cupSize
     */
    @PutMapping(value = "/girls/{id}")
    public Girl girlUpdate(@PathVariable("id") Integer id,
                           @RequestParam("age") Integer age,
                           @RequestParam("cupSize") String cupSize){

        Girl girl = new Girl();
        girl.setId(id);
        girl.setAge(age);
        girl.setCupSize(cupSize);

        return  girlRepository.save(girl);
    }

    /**
     * 删除一个女生
     * @param id
     */
    @DeleteMapping(value = "/girls/{id}")
    public void girlDelete(@PathVariable("id") Integer id){
        girlRepository.delete(id);
    }

    /**
     * 通过年龄查询女生列表
     * @param age
     * @return
     */
    @GetMapping(value = "/girls/age/{age}")
    public List<Girl> girlListByAge(@PathVariable("age") Integer age){
        return  girlRepository.findByAge(age);
    }
}
