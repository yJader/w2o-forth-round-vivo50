package com.yj.controller;

import com.yj.domain.ResponseResult;
import com.yj.domain.dto.LoginUserDTO;
import com.yj.enums.AppHttpCodeEnum;
import com.yj.exception.SystemException;
import com.yj.service.PlatLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
    public ResponseResult login(@RequestBody LoginUserDTO loginUserDTO){
        return platLoginService.login(loginUserDTO);
    }

    @PostMapping("/logout")
    @ApiOperation(value = "退出")
    public ResponseResult logout(){
        return platLoginService.logout();
    }
}
