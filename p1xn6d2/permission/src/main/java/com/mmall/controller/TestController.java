package com.mmall.controller;

import com.mmall.common.ApplicationContextHelper;
import com.mmall.common.JsonData;
import com.mmall.dao.SysAclModuleMapper;
import com.mmall.exception.ParamException;
import com.mmall.exception.PermissionException;
import com.mmall.model.SysAclModule;
import com.mmall.param.TestVo;
import com.mmall.util.BeanValidator;
import com.mmall.util.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @ClassName : TestController Created with IDEA
 *
 * @author:CarlLing @CreateDate : 2019-01-09 23:44 @Description :
 */
@Controller
@RequestMapping("/test")
@Slf4j
public class TestController {

  protected static final Logger log = LoggerFactory.getLogger(TestController.class);

  @RequestMapping("/hello.json")
  @ResponseBody
  public JsonData hello() {
    log.info("----------------hello---------------");
    // return "hello, permission!";
    //throw new PermissionException("test exception");
     return  JsonData.success("hello , permission");
  }

  @RequestMapping("/validate.json")
  @ResponseBody
  public JsonData validate(TestVo vo) throws ParamException{
    log.info("----validate-----");

    SysAclModuleMapper moduleMapper = ApplicationContextHelper.popBean(SysAclModuleMapper.class);
    SysAclModule module = moduleMapper.selectByPrimaryKey(15);
    log.info(JsonMapper.obj2String(module));

    /* try {
      Map<String, String> map = BeanValidator.validateObject(vo);
      // if(map != null && map.entrySet().size() > 0){
      if (MapUtils.isEmpty(map)) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
          log.info("{}->{} ", entry.getKey(), entry.getValue());
        }
      }
    } catch (Exception e) {

    }*/
   BeanValidator.check(vo);
    return JsonData.success("test validate !!!");
  }
}
