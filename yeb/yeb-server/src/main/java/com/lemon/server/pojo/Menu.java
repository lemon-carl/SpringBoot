package com.lemon.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

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
@TableName("t_menu")
@ApiModel(value="Menu对象", description="")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜单id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(value = "菜单id")
    private String url;
    @ApiModelProperty(value = "菜单id")
    private String path;
    @ApiModelProperty(value = "组件")
    private String component;
    @ApiModelProperty(value = "菜单名")
    private String name;
    @ApiModelProperty(value = "图标")
    private String iconCls;
    @ApiModelProperty(value = "是否保持激活")
    private Boolean keepAlive;
    @ApiModelProperty(value = "是否要求权限")
    private Boolean requireAuth;
    @ApiModelProperty(value = "父id")
    private Integer parentId;
    @ApiModelProperty(value = "是否启用")
    private Boolean enabled;

    @ApiModelProperty(value = "子菜单")
    @TableField(exist = false)
    private List<Menu> children;

}
