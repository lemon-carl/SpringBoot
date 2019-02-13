package com.mmall.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @Auth CarlLing
 * @Date 2019/2/13 13:41
 * @Version 1.0
 * @Desc
 */
@Setter
@Getter
@ToString
public class SearchLogDto {

    private Integer type; // LogType

    private String beforeSeg;

    private String afterSeg;

    private String operator;

    private Date fromTime;//yyyy-MM-dd HH:mm:ss

    private Date toTime; //yyyy-MM-dd HH:mm:ss
}
