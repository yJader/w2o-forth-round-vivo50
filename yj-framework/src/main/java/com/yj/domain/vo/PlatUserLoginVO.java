package com.yj.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Package com.yj.domain.vo
 * @Author yJade
 * @Date 2023-02-18 21:50
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlatUserLoginVO {
    @ApiModelProperty("登录令牌")
    private String token;
    private UserInfoVO userInfoVO;
}
