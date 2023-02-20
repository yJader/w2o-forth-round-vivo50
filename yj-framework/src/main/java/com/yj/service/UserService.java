package com.yj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yj.domain.ResponseResult;
import com.yj.domain.entity.User;
import com.yj.domain.vo.UserInfoVO;

/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2023-02-20 17:36:29
 */
public interface UserService extends IService<User> {

    /**
     * @description: 获取用户信息
     * @param: null
     * @return: com.yj.domain.ResponseResult<com.yj.domain.vo.UserInfoVO>
     * @author: YJader
     * @date: 2023/2/20 17:49
     */
    ResponseResult<UserInfoVO> userInfo();

    ResponseResult updateUserInfo(User user);

    ResponseResult register(User user);
}
