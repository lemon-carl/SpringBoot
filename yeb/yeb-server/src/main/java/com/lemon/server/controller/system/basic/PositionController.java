package com.lemon.server.controller.system.basic;


import com.lemon.server.model.Position;
import com.lemon.server.pojo.common.RespBean;
import com.lemon.server.service.IPositionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * 职位管理 (基础信息模块)
 *
 * @author lemon
 * @since 2021-04-07
 */
@RestController
@RequestMapping("/system/basic/pos")
public class PositionController {
    @Autowired
    private IPositionService positionService;

    @ApiOperation("获取所有职位信息")
    @GetMapping("/")
    public List<Position> getAllPosition() {
        return positionService.list();
    }

    @ApiOperation("添加职位信息")
    @PostMapping("/")
    public RespBean addPosition(@RequestBody Position position) {
        position.setCreateDate(LocalDateTime.now());
        if (positionService.save(position)) {
            return RespBean.ok("添加成功！");
        }

        return RespBean.error("添加失败！");
    }

    @ApiOperation("更新职位信息")
    @PutMapping("/")
    public RespBean updatePosition(@RequestBody Position position) {
        if (positionService.updateById(position)) {
            return RespBean.ok("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation("删除职位信息")
    @DeleteMapping("/{id}")
    public RespBean deletePosition(@PathVariable("id") Integer id) {
        if (positionService.removeById(id)) {
            return RespBean.ok("删除成功！");
        }
        return RespBean.error("删除失败！");

    }

    @ApiOperation("批量删除职位信息")
    @DeleteMapping("/")
    public RespBean deletePositionByIds(Integer[] ids) {
        if (positionService.removeByIds(Arrays.asList(ids))) {
            return RespBean.ok("批量删除成功！");
        }
        return RespBean.error("批量删除失败！");

    }


}
