package com.yj.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

/**
 * @Description:
 * @Package com.yj.domain.dto
 * @Author yJade
 * @Date 2023-02-27 20:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "分页DTO")
public class PageDTO {

    @Min(value = 1, message = "页码过小")
    @ApiModelProperty("当前页码(从1开始)")
    Integer pageNum;

    @Min(value = 1, message = "每页展示量过小")
    @ApiModelProperty("每页展示量")
    Integer pageSize;
}
