package com.yj.service.impl;

import com.yj.constants.RedisKeyConstants;
import com.yj.domain.ResponseResult;
import com.yj.domain.entity.LoginUser;
import com.yj.domain.entity.User;
import com.yj.domain.vo.PlatUserLoginVO;
import com.yj.domain.vo.UserInfoVO;
import com.yj.enums.AppHttpCodeEnum;
import com.yj.exception.SystemException;
import com.yj.service.PlatLoginService;
import com.yj.utils.BeanCopyUtils;
import com.yj.utils.JwtUtil;
import com.yj.utils.RedisCache;
import com.yj.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @Description:
 * @Package com.yj.service.impl
 * @Author yJade
 * @Date 2023-02-18 20:55
 */
@Service
public class PlatLoginServiceImpl implements PlatLoginService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //判断是否认证通过
        if (Objects.isNull(authenticate)){
            throw new SystemException(AppHttpCodeEnum.LOGIN_ERROR);
        }
        //获取userid 生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //讲用户信息存入redis
        redisCache.setCacheObject(RedisKeyConstants.PLATFORM_LOGIN + userId, loginUser);

        //讲token和userinfo封装 返回
        UserInfoVO userInfoVO = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVO.class);
        PlatUserLoginVO vo = new PlatUserLoginVO(jwt, userInfoVO);
        return ResponseResult.okResult(vo);
    }

    @Override
    public ResponseResult logout() {
        Long id = SecurityUtils.getUserId();
        redisCache.deleteObject(RedisKeyConstants.PLATFORM_LOGIN+id);
        return ResponseResult.okResult();
    }
}
