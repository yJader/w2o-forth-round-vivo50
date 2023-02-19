package com.yj.domain.vo;

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
    private String token;
    private UserInfoVO userInfoVO;
}
