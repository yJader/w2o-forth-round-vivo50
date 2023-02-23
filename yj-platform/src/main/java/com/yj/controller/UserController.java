package com.yj.controller;

import com.yj.annotation.SystemLog;
import com.yj.domain.ResponseResult;
import com.yj.domain.dto.InputPointsDTO;
import com.yj.domain.dto.RegisterDTO;
import com.yj.domain.dto.UserInfoDTO;
import com.yj.domain.entity.User;
import com.yj.domain.vo.UserInfoVO;
import com.yj.service.UserService;
import com.yj.utils.BeanCopyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
    @SystemLog(businessName = "修改用户信息")
    @ApiOperation(value = "修改用户信息")
    public ResponseResult updateUserInfo(@RequestBody UserInfoDTO userInfoDTO){
        User user = BeanCopyUtils.copyBean(userInfoDTO, User.class);
        return userService.updateUserInfo(user);
    }

    @PostMapping("/register")
    @SystemLog(businessName = "注册")
    @ApiOperation(value = "注册")
    public ResponseResult register(@Validated @RequestBody RegisterDTO registerDTO){
        User user = BeanCopyUtils.copyBean(registerDTO, User.class);
        return userService.register(user);
    }

    @GetMapping("/signIn")
    @ApiOperation(value = "签到")
    public ResponseResult signIn() {
        return userService.signIn();
    }

    @PostMapping("/inputPoints")
    @ApiOperation(value = "投积分")
    public ResponseResult inputPoints(@RequestBody InputPointsDTO inputPointsDTO) {
        return userService.inputPoints(inputPointsDTO.getTargetProjectId(), inputPointsDTO.getInputPoints());
    }
}