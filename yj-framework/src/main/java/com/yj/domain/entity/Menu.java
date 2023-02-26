package com.yj.domain.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 菜单权限表(Menu)表实体类
 *
 * @author makejava
 * @since 2023-02-26 11:50:10
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_menu")
@ApiModel("菜单权限表")
public class Menu  {
    /**
    * 菜单ID
    */
    @TableId
    @ApiModelProperty("菜单ID")
    private Long id;
    /**
    * 菜单名称
    */    
    @ApiModelProperty("菜单名称")
    private String menuName;
    /**
    * 父菜单ID
    */    
    @ApiModelProperty("父菜单ID")
    private Long parentId;
    /**
    * 显示顺序
    */    
    @ApiModelProperty("显示顺序")
    private Integer orderNum;
    /**
    * 路由地址
    */    
    @ApiModelProperty("路由地址")
    private String path;
    /**
    * 组件路径
    */    
    @ApiModelProperty("组件路径")
    private String component;
    /**
    * 是否为外链（0是 1否）
    */    
    @ApiModelProperty("是否为外链（0是 1否）")
    private Integer isFrame;
    /**
    * 菜单类型（M目录 C菜单 F按钮）
    */    
    @ApiModelProperty("菜单类型（M目录 C菜单 F按钮）")
    private String menuType;
    /**
    * 菜单状态（0显示 1隐藏）
    */    
    @ApiModelProperty("菜单状态（0显示 1隐藏）")
    private String visible;
    /**
    * 菜单状态（0正常 1停用）
    */    
    @ApiModelProperty("菜单状态（0正常 1停用）")
    private String status;
    /**
    * 权限标识
    */    
    @ApiModelProperty("权限标识")
    private String perms;
    /**
    * 菜单图标
    */    
    @ApiModelProperty("菜单图标")
    private String icon;
    /**
    * 备注
    */    
    @ApiModelProperty("备注")
    private String remark;
    /**
    * 删除标志
    */    
    @ApiModelProperty("删除标志")
    private String delFlag;

}

