package com.lemon.server.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 
 * </p>
 *
 * @author lemon
 * @since 2021-04-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_mail_log")
@ApiModel(value="MailLog对象", description="")
public class MailLog implements Serializable {

    private static final long serialVersionUID = 1L;

    private String msgId;

    private Integer empId;

    @ApiModelProperty(value = "0发送中，1发送成功，2发送失败")
    private Integer status;

    private String routeKey;

    private String exchange;

    @ApiModelProperty(value = "重试次数")
    private Integer count;

    @ApiModelProperty(value = "第一次重试时间")
    private LocalDate tryTime;

    private LocalDate createTime;

    private LocalDate updateTime;


}
