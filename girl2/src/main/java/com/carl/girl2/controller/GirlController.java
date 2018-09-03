package com.carl.girl2.controller;

import com.carl.girl2.entity.Girl;
import com.carl.girl2.entity.Result;
import com.carl.girl2.respository.GirlRepository;
import com.carl.girl2.service.GirlService;
import com.carl.girl2.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName : GirlController Created with IDEA
 *
 * @author:CarlLing @CreateDate : 2018-08-16 23:30 @Description :
 */
@RestController
public class GirlController {

  private static final Logger logger = LoggerFactory.getLogger(GirlController.class);

  @Autowired private GirlRepository girlRepository;

  @Autowired private GirlService girlService;

  /**
   * 获取女生列表
   *
   * @return
   */
  @GetMapping(value = "/girls")
  public List<Girl> girlList() {
    logger.info("grilList");
    return girlRepository.findAll();
  }

  /**
   * 添加一个女生
   *
   * @param girl
   * @return
   */
  @PostMapping(value = "/girls")
  public /*  Girl*/ Result<Girl> girlAdd(@Valid Girl girl, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResultUtil.error(1, bindingResult.getFieldError().getDefaultMessage());
    }

    girl.setCupSize(girl.getCupSize());
    girl.setAge(girl.getAge());

    return ResultUtil.success(girlRepository.save(girl));
    //  return girlRepository.save(girl);
  }

  /**
   * 查询一个女生
   *
   * @return
   */
  @GetMapping(value = "/girls/{id}")
  public Girl girlFindOne(@PathVariable("id") Integer id) {
    return girlRepository.findOne(id);
  }

  /**
   * 更新一个女生
   *
   * @param id
   * @param cupSize
   * @param age
   * @return
   */
  @PutMapping(value = "/girls/{id}")
  public Girl girlUpdate(
      @PathVariable("id") Integer id,
      @RequestParam("cupSize") String cupSize,
      @RequestParam("age") Integer age) {
    Girl girl = new Girl();
    girl.setId(id);
    girl.setCupSize(cupSize);
    girl.setAge(age);

    return girlRepository.save(girl);
  }

  /**
   * 删除一个女生
   *
   * @param id
   */
  @DeleteMapping(value = "/girls/{id}")
  public void girlDelete(@PathVariable("id") Integer id) {
    girlRepository.delete(id);
  }

  /**
   * 通过年龄查询女生列表
   *
   * @param age
   * @return
   */
  @GetMapping(value = "/girls/age/{age}")
  public List<Girl> girlListByAge(@PathVariable("age") Integer age) {
    return girlRepository.findByAge(age);
  }

  @PostMapping(value = "/girls/two")
  public void girlTwo() {
    girlService.insertTwo();
  }

  @GetMapping(value = "girls/getAge/{id}")
  public void getAge(@PathVariable("id") Integer id) throws Exception{
    girlService.getAge(id);

  }
}
