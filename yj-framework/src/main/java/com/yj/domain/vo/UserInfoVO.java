package com.yj.domain.vo;

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

    private String phonenumber;
}
