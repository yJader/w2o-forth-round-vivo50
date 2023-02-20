package com.yj.domain.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Package com.yj.domain.dto
 * @Author yJade
 * @Date 2023-02-18 20:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("登录用户数据传输对象")
public class LoginUserDTO {
    private String userName;
    private String password;
}
