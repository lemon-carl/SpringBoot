package com.mmall.controller;

import com.mmall.beans.PageQuery;
import com.mmall.common.JsonData;
import com.mmall.dto.SearchLogDto;
import com.mmall.param.SearchLogParam;
import com.mmall.service.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @ClassName : SysLogController
 * Created with IDEA
 * @author:CarlLing
 * @CreateDate : 2019-02-12 23:09
 * @Description : 操作日志接口
 */
@Slf4j
@Controller
@RequestMapping("/sys/log")
public class SysLogController {

    @Resource private SysLogService sysLogService;

    @RequestMapping("/log.page")
    public ModelAndView page() {
        return new ModelAndView("log");
    }

    @RequestMapping("/page.json")
    @ResponseBody
    public JsonData searchPage(SearchLogParam param, PageQuery page){
        return JsonData.success(sysLogService.searchPageList(param,page));
    }

    @RequestMapping("/recover.json")
    @ResponseBody
    public JsonData recover(@RequestParam("id") int id) {
        log.info("recover............");
        sysLogService.recover(id);
        return JsonData.success();
    }

}
