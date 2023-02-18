package com.yj.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Package com.yj.controller
 * @Author yJade
 * @Date 2023-02-18 1:19
 */
@Api(description = "测试相关接口")
@RestController
public class HelloController {

    @ApiOperation(value = "hello", notes = "返回一个hello和ip")
    @GetMapping("/hello")
    public String hello(HttpServletRequest request) {
        return "Hello"+request.getRemoteAddr()+"\tzzz";
    }
}
