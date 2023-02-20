package com.yj.domain.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.yj.domain.entity.Pictures;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @Description:
 * @Package com.yj.domain.dto
 * @Author yJade
 * @Date 2023-02-19 23:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("新项目数据传输对象")
public class NewProjectDTO {
    /**
     * 标题
     */
    @NotBlank(message = "标题不能为空")
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
     * 众筹目标
     */
    @ApiModelProperty("众筹目标")
    private Long target;
    /**
     * 是否允许评论 (1是, 0否)
     */
    @ApiModelProperty("是否允许评论 (1是, 0否)")
    private String isComment;
}
