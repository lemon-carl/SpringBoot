package com.mmall.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Auth CarlLing
 * @Date 2019/2/13 13:35
 * @Version 1.0
 * @Desc    日志搜索参数
 */
@Getter
@Setter
@ToString
public class SearchLogParam {

    private Integer type; // LogType 日志类型

    private String beforeSeg;

    private String afterSeg;

    private String operator;

    private String fromTime;//yyyy-MM-dd HH:mm:ss

    private String toTime;
}
