package com.yj.controller;

import com.yj.domain.ResponseResult;
import com.yj.domain.dto.UserInfoDTO;
import com.yj.domain.entity.User;
import com.yj.domain.vo.UserInfoVO;
import com.yj.service.UserService;
import com.yj.utils.BeanCopyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Package com.yj.controller
 * @Author yJade
 * @Date 2023-02-20 17:31
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户", description = "用户相关请求")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userInfo")
    @ApiOperation(value = "用户详情")
    public ResponseResult<UserInfoVO> userInfo() {
        return userService.userInfo();
    }

    @PutMapping("/userInfo")
    @ApiOperation(value = "修改用户信息")
    public ResponseResult updateUserInfo(@RequestBody UserInfoDTO userInfoDTO){
        User user = BeanCopyUtils.copyBean(userInfoDTO, User.class);
        return userService.updateUserInfo(user);
    }
}
