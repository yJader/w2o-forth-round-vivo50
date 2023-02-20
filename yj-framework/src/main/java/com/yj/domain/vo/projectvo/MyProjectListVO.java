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
 * @Date 2023-02-19 21:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户发布的项目列表值对象")
public class MyProjectListVO {
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

}