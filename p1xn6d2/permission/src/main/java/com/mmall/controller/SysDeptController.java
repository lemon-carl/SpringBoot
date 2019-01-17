package com.mmall.controller;

import com.mmall.common.JsonData;
import com.mmall.dto.DeptLevelDto;
import com.mmall.param.DeptParam;
import com.mmall.service.SysDeptService;
import com.mmall.service.impl.SysTreeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName : SysDeptController Created with IDEA
 *
 * @author:CarlLing @CreateDate : 2019-01-13 17:50 @Description : 部门管理
 */
@Controller
@RequestMapping("/sys/dept")
@Slf4j
public class SysDeptController {

  protected  static final Logger log = LoggerFactory.getLogger(SysDeptController.class);

  @Resource private SysDeptService sysDeptService;
  @Resource private SysTreeServiceImpl sysTreeService;

  /*@RequestMapping("dept.page")
  public ModelAndView page(){
    return new ModelAndView("dept");
  }*/

  /**
   * 新增部门功能
   * @param param 部门参数
   * @return
   */
  @RequestMapping("/save.json")
  @ResponseBody
  public JsonData saveDept(DeptParam param) {
    sysDeptService.save(param);
    return JsonData.success();
  }

  /**
   * 构建部门树
   * @return
   */
  @RequestMapping("/tree.json")
  @ResponseBody
  public JsonData tree() {
    List<DeptLevelDto> dtoList = sysTreeService.deptTree();
    return JsonData.success(dtoList);
  }

  /**
   *  更新部门
   * @param param
   * @return
   */
  @RequestMapping("/update.json")
  @ResponseBody
  public JsonData updateDept(DeptParam param) {
    sysDeptService.update(param);
    return JsonData.success();
  }

  /**
   * 删除部门
   * @param id
   * @return
   */
 /* @RequestMapping("/delete.json")
  @ResponseBody
  public JsonData delete(@RequestParam("id") int id) {
    sysDeptService.delete(id);
    return JsonData.success();
  }*/

}
