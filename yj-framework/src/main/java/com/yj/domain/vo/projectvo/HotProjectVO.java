package com.yj.domain.vo.projectvo;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Package com.yj.domain.vo
 * @Author yJade
 * @Date 2023-02-18 14:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("热门项目值对象")
public class HotProjectVO {
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
}

