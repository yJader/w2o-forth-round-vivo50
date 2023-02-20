package com.yj.domain.vo.projectvo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
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
 * @Date 2023-02-18 15:36
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("项目列表值对象")
public class ProjectListVO {
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
     * 浏览量
     */
    @ApiModelProperty("浏览量")
    private Long viewCount;
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
}
