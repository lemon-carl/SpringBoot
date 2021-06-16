package com.lemon.server.pojo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *  分页公共返回对象
 * @author: Lemon
 * @Date : 2021/6/15 22:14
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespPageBean {

    /**
     * 总条数
     */
    private Long total;

    /**
     * 数据list
     */
    private List<?> data;
}
