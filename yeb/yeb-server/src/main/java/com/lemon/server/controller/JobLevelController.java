package com.lemon.server.controller;


import com.lemon.server.model.JobLevel;
import com.lemon.server.pojo.common.RespBean;
import com.lemon.server.service.IJobLevelService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * 职称管理 (基础信息模块)
 *
 * @author lemon
 * @since 2021-04-07
 */
@RestController
@RequestMapping("/system/basic/jobLevel")
public class JobLevelController {
    @Autowired
    private IJobLevelService jobLevelService;

    @ApiOperation("获取所有职称")
    @GetMapping("/")
    public List<JobLevel> get() {
        return jobLevelService.list();
    }

    @ApiOperation("添加职称")
    @PostMapping("/")
    public RespBean add(@RequestBody JobLevel joblevel) {
        joblevel.setCreateDate(LocalDateTime.now());
        if (jobLevelService.save(joblevel)) {
            return RespBean.ok("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @ApiOperation("更新职称")
    @PutMapping("/")
    public RespBean update(@RequestBody JobLevel joblevel) {
        if (jobLevelService.updateById(joblevel)) {
            return RespBean.ok("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation("删除职称")
    @DeleteMapping("/{id}")
    public RespBean delete(@PathVariable("id") Integer id) {
        if (jobLevelService.removeById(id)) {
            return RespBean.ok("删除成功！");
        }
        return RespBean.error("删除失败！");
    }

    @ApiOperation("批量删除职称")
    @DeleteMapping("/")
    public RespBean deleteByIds(Integer[] ids) {
        if (jobLevelService.removeByIds(Arrays.asList(ids))) {
            return RespBean.ok("批量删除成功！");
        }
        return RespBean.error("批量删除失败！");
    }
}
