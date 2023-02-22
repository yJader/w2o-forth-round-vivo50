package com.yj.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * (Project)表实体类
 *
 * @author makejava
 * @since 2023-02-18 15:32:33
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "yj_project", autoResultMap = true) //操作json必须开启autoResultMap
@Accessors(chain = true) //支持链式编程
@ApiModel("项目")
public class Project  {
    /**
    * 众筹项目id
    */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("众筹项目id")
    private Long id;
    /**
    * 标题
    */
    @ApiModelProperty("标题")
    private String title;
    /**
    * 描述
    */    
    @ApiModelProperty("描述")
    private String content;
    /**
    * 证明图片(使用oss存储)
    */
    @ApiModelProperty("证明图片(使用oss存储)")
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private Pictures pictures;
    /**
    * 项目简介
    */    
    @ApiModelProperty("项目简介")
    private String summary;
    /**
    * 缩略图(使用oss存储)
    */
    @ApiModelProperty("缩略图(使用oss存储)")
    private String thumbnail;
    /**
    * 当前进度
    */    
    @ApiModelProperty("当前进度")
    private Long current;
    /**
    * 众筹目标
    */    
    @ApiModelProperty("众筹目标")
    private Long target;
    /**
    * 状态 (0已发布, 1草稿, 2正在审核)
    */
    @ApiModelProperty("状态 (0已发布, 1草稿, 2正在审核)")
    private String status;
    /**
    * 浏览量
    */    
    @ApiModelProperty("浏览量")
    private Long viewCount;
    /**
    * 是否允许评论 (1是, 0否)
    */    
    @ApiModelProperty("是否允许评论 (1是, 0否)")
    private String isComment;
    /**
    * 发布人id
    */
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("发布人id")
    private Long createBy;
    /**
    * 发布时间
    */
    @TableField(fill = FieldFill.INSERT)

    @ApiModelProperty("发布时间")
    private Date createTime;
    /**
    * 审核人id
    */    
    @ApiModelProperty("审核人id")
    private Long examineBy;
    /**
    * 审核时间
    */    
    @ApiModelProperty("审核时间")
    private Date examineTime;
    /**
    * 删除标志(0未删除, 1已删除)
    */    
    @ApiModelProperty("删除标志(0未删除, 1已删除)")
    private String delFlag;

    public Project(Long id, Long viewCount) {
        this.id = id;
        this.viewCount = viewCount;
    }
}