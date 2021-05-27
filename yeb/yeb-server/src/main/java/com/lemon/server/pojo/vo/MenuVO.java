package com.lemon.server.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.lemon.server.model.Menu;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @des:
 * @author: Lemon
 * @Date : 2021/5/27 16:41
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Menu对象", description = "")
public class MenuVO {

    @ApiModelProperty(value = "菜单id")
    private Integer id;

    @ApiModelProperty(value = "菜单名")
    private String name;

    @ApiModelProperty(value = "子菜单")
    @TableField(exist = false)
    private List<MenuVO> children;
}
