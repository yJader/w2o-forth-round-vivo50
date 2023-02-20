package com.yj.controller;

import com.yj.domain.entity.LoginUser;
import com.yj.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping({"/test"})
@Api(
    tags = {"测试用接口"}
)
public class TestController {

    @ApiOperation(value = "hello", notes = "返回一个hello和ip")
    @GetMapping("/hello")
    public String hello(HttpServletRequest request) {
        return "Hello"+request.getRemoteAddr()+"\tzzz";
    }

    @ApiOperation("测试拦截")
    @GetMapping({"/auth"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", value = "令牌", required = true),
    })
    public String testAuthByGet() {
        Authentication authentication = SecurityUtils.getAuthentication();
        LoginUser loginUser = (LoginUser)authentication.getPrincipal();
        return "hello";
    }

}