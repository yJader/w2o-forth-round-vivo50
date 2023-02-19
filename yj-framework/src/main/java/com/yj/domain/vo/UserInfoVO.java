package com.yj.domain.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Description:
 * @Package com.yj.domain.vo
 * @Author yJade
 * @Date 2023-02-18 21:44
 */
@Data
@Accessors(chain = true)
public class UserInfoVO {
    /**
     * 主键
     */
    private Long id;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 头像
     */
    private String avatar;

    private String sex;

    private String email;
}
