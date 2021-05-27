package com.lemon.server.controller;


import com.lemon.server.service.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
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



}
