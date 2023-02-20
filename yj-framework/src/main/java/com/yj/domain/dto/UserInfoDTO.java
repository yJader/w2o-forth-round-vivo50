package com.yj.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @Description:
 * @Package com.yj.domain.vo
 * @Author yJade
 * @Date 2023-02-18 21:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
//支持链式编程 有啥用忘了 阿巴阿巴
@Accessors(chain = true)
@ApiModel("用户信息DTO")
public class UserInfoDTO {
    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称, 长度为1~10个字符")
    @NotBlank(message = "昵称不能为空")
    @Length(min = 1, max = 10, message = "昵称长度错误, 应在1~10个字符之间")
    private String nickName;
    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    @NotBlank(message = "头像不能为空")
    private String avatar;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty("邮箱")
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式错误")
    private String email;

    @ApiModelProperty("手机号")
    @NotBlank(message = "手机号不能为空")
    @Length(min = 11, max = 11, message = "手机号格式错误")
    private String phonenumber;
}
