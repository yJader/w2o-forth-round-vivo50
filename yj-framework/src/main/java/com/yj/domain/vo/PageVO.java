package com.yj.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description:
 * @Package com.yj.domain.vo
 * @Author yJade
 * @Date 2023-02-18 16:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("分页VO")
public class PageVO<T> {
    private List<T> rows;
    /**
     * 总记录数
     */
    @ApiModelProperty("总记录数")
    private Long total;
    /**
     * 总页数
     */
    @ApiModelProperty("总页数")
    private Long pageTotal;
}

