package com.lemon.server.controller;


import com.lemon.server.model.Joblevel;
import com.lemon.server.pojo.common.RespBean;
import com.lemon.server.service.IJoblevelService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 职称管理
 *
 * @author lemon
 * @since 2021-04-07
 */
@RestController
@RequestMapping("/system/basic/joblevel")
public class JobLevelController {
    @Autowired
    private IJoblevelService joblevelService;

    @ApiOperation("获取所有职称")
    @GetMapping("/")
    public List<Joblevel> get() {
        return joblevelService.list();
    }

    @ApiOperation("添加职称")
    @PostMapping("/")
    public RespBean add(@RequestBody Joblevel joblevel) {
        joblevel.setCreateDate(LocalDateTime.now());
        if (joblevelService.save(joblevel)) {
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }
}
