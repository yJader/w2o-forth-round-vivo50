package com.yj.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @Description:
 * @Package com.yj.domain.dto
 * @Author yJade
 * @Date 2023-02-18 20:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("登录用户DTO")
public class LoginUserDTO {
    @ApiModelProperty(notes = "用户名")
    @NotBlank(message = "用户名不能为空")
    @Length(min = 3, max = 20, message = "用户名长度错误")
    private String userName;

    @ApiModelProperty(notes = "密码")
    @NotBlank(message = "密码不能为空")
    @Length(min = 3, max = 20, message = "密码长度错误")
    private String password;
}
