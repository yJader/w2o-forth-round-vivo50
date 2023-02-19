package com.yj.service;

import com.yj.domain.ResponseResult;
import com.yj.domain.dto.LoginUserDTO;

/**
 * @Description:
 * @Package com.yj.service
 * @Author yJade
 * @Date 2023-02-18 20:54
 */
public interface PlatLoginService {

    ResponseResult login(LoginUserDTO loginUserDTO);

    ResponseResult logout();
}
