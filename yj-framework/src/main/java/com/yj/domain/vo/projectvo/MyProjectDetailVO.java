package com.yj.domain.vo.projectvo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.yj.domain.entity.Pictures;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description:
 * @Package com.yj.domain.vo
 * @Author yJade
 * @Date 2023-02-18 16:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("项目详情值对象")
public class MyProjectDetailVO {
    /**
     * 众筹项目id
     */
    @TableId
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
    @ApiModelProperty("发布人id")
    private Long createBy;
    /**
     * 发布时间
     */
    @ApiModelProperty("发布时间")
    private Date createTime;
    /**
     * 状态 (0已发布, 1草稿, 2正在审核)
     */
    @ApiModelProperty("状态 (0已发布, 1草稿, 2正在审核)")
    private String status;
}
