package com.yj.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

/**
 * @Description:
 * @Package com.yj.domain.dto
 * @Author yJade
 * @Date 2023-02-23 19:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("项目搜索DTO")
public class SearchProjectDTO {
    @ApiModelProperty(value = "项目名称")
    private String projectTitle;

    @ApiModelProperty(value = "创建用户id")
    private String userId;

    @ApiModelProperty(hidden = true)
    public boolean notNull() {
        return StringUtils.hasText(projectTitle) || StringUtils.hasText(userId);
    }
}
