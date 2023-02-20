package com.yj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yj.domain.ResponseResult;
import com.yj.domain.entity.User;
import com.yj.domain.vo.UserInfoVO;
import com.yj.enums.AppHttpCodeEnum;
import com.yj.exception.SystemException;
import com.yj.mapper.UserMapper;
import com.yj.service.UserService;
import com.yj.utils.BeanCopyUtils;
import com.yj.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2023-02-20 17:36:29
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseResult<UserInfoVO> userInfo() {
        //获取当前用户id
        Long userId = SecurityUtils.getUserId();
        //根据用户id查询用户信息
        User user = getById(userId);
        //封装成UserInfoVO
        UserInfoVO vo = BeanCopyUtils.copyBean(user,UserInfoVO.class);
        return ResponseResult.okResult(vo);
    }

    @Override
    public ResponseResult updateUserInfo(User user) {
        user.setId(SecurityUtils.getUserId());
        updateById(user);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult register(User user) {
        //对数据进行重复判断
        if (isExist(user.getUserName(), User::getUserName)) {
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        if (isExist(user.getNickName(), User::getNickName)) {
            throw new SystemException(AppHttpCodeEnum.NICKNAME_EXIST);
        }
        if (isExist(user.getPhonenumber(), User::getPhonenumber)) {
            throw new SystemException(AppHttpCodeEnum.PHONENUMBER_EXIST);
        }
        if (isExist(user.getEmail(), User::getEmail)) {
            throw new SystemException(AppHttpCodeEnum.EMAIL_EXIST);
        }
        //密码加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //存入数据库
        save(user);

        return ResponseResult.okResult();
    }

    private boolean isExist(String variable, SFunction<User, ?> function) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(function, variable);
        return count(queryWrapper) > 0;
    }
}

