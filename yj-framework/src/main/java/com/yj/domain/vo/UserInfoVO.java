package com.yj.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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
@ApiModel("用户信息VO")
public class UserInfoVO {
    /**
     * 主键
     */
    @ApiModelProperty("用户id")
    private Long id;
    /**
     * 昵称
     */
    @ApiModelProperty("昵称")
    private String nickName;
    /**
     * 头像
     */
    @ApiModelProperty("头像")
    private String avatar;
    /**
     * 用户性别（0男，1女，2未知）
     */
    @ApiModelProperty("用户性别（0男，1女，2未知）")
    private String sex;
    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String email;
    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String phonenumber;

    /**
     * 用户积分
     */
    @ApiModelProperty("用户积分")
    private Integer points;
}
