package com.yj.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Package com.yj.domain.vo
 * @Author yJade
 * @Date 2023-03-01 21:03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("投入积分VO")
public class InputPointsVO {
    @ApiModelProperty("成功投入积分")
    private Integer inputPoints;
    @ApiModelProperty("用户剩余积分")
    private Integer restPoints;
}
