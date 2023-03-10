package com.yj.domain.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.yj.domain.entity.Pictures;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @Description:
 * @Package com.yj.domain.dto
 * @Author yJade
 * @Date 2023-02-19 23:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("修改项目DTO")
public class UpdateProjectDTO {
    /**
     * 众筹项目id
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("众筹项目id")
    @NotNull(message = "项目id不能为空")
    private Long id;
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

    /**
     * 更改后的状态 (0已发布, 1草稿, 2正在审核)
     */
    @Pattern(regexp="^[12]$", message = "修改为草稿或者是正在审核")
    @ApiModelProperty("更改后的状态 (0已发布, 1草稿, 2正在审核), 并且只能修改为1或2")
    private String status;
}
