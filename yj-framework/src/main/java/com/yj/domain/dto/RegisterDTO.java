package com.yj.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @Description:
 * @Package com.yj.domain.dto
 * @Author yJade
 * @Date 2023-02-20 22:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户注册DTO")
public class RegisterDTO {
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名, 长度为3~20个字符")
    @NotBlank(message = "用户名不能为空")
    @Length(min = 3, max = 20, message = "用户名长度错误, 应在3~20个字符之间")
    private String userName;
    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称, 长度为1~10个字符")
    @NotBlank(message = "昵称不能为空")
    @Length(min = 1, max = 10, message = "昵称长度错误, 应在1~10个字符之间")
    private String nickName;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码, 长度为3~20个字符")
    @NotBlank(message = "密码不能为空")
    @Length(min = 3, max = 20, message = "密码长度错误, 应在3~20个字符之间")
    private String password;
    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式错误")
    private String email;
    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    @NotBlank(message = "手机号不能为空")
    @Length(min = 11, max = 11, message = "手机号格式错误")
    private String phonenumber;
}
