package com.yj.controller;

import com.yj.domain.ResponseResult;
import com.yj.domain.dto.LoginUserDTO;
import com.yj.domain.entity.User;
import com.yj.service.PlatLoginService;
import com.yj.utils.BeanCopyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Package com.yj.controller
 * @Author yJade
 * @Date 2023-02-18 20:49
 */
@RestController
@Api(tags = "登录&退出")
public class PlatLoginController {

    @Autowired
    private PlatLoginService platLoginService;

    @PostMapping("/login")
    @ApiOperation(value = "登录")
    public ResponseResult login(@RequestBody LoginUserDTO loginUserDTO) {
        User user = BeanCopyUtils.copyBean(loginUserDTO, User.class);
        return platLoginService.login(user);
    }

    @PostMapping("/logout")
    @ApiOperation(value = "退出")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", value = "令牌", required = true),
    })
    public ResponseResult logout() {
        return platLoginService.logout();
    }
}
