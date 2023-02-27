package com.yj.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @Description:
 * @Package com.yj.domain.dto
 * @Author yJade
 * @Date 2023-02-23 13:55
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("投入积分DTO")
public class InputPointsDTO {

    @ApiModelProperty(notes = "目标项目")
    @NotNull(message = "目标项目不能为空")
    private Long targetProjectId;

    @ApiModelProperty(notes = "投入积分")
    @Min(value = 1, message = "必须大于等于1")
    @Max(value = 3, message = "必须小于等于设定值(目前为3)")
    private Integer inputPoints;
}
