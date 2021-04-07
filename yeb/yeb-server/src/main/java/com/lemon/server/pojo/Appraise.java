package com.lemon.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("t_appraise")
@ApiModel(value="Appraise对象", description="")
public class Appraise implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer eid;

    @ApiModelProperty(value = "考评日期")
    private LocalDate appDate;

    @ApiModelProperty(value = "考评结果")
    private String appResult;

    @ApiModelProperty(value = "考评内容")
    private String appContent;

    @ApiModelProperty(value = "备注")
    private String remark;


}
