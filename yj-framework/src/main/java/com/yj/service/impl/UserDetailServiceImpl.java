package com.yj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yj.constants.SystemConstants;
import com.yj.domain.entity.LoginUser;
import com.yj.domain.entity.User;
import com.yj.mapper.MenuMapper;
import com.yj.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Description:
 * @Package com.yj.service.impl
 * @Author yJade
 * @Date 2023-02-18 21:28
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private UserMapper userMapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,username);
        User user = userMapper.selectOne(queryWrapper);
        //判断是否查到用户  如果没查到抛出异常
        if(Objects.isNull(user)){
            throw new RuntimeException("用户不存在");
        }

        // 查询权限信息封装
        List<String> perms = new ArrayList<>();

        if (user.getId().equals(SystemConstants.ROOT_ADMIN_USER_ID)) {
            // 如果是超级管理员 直接赋予所有权限
            perms = menuMapper.selectAllPerms();
        } else {
            // 其他用户查询权限并封装
            perms = menuMapper.selectPermsByUserId(user.getId());
        }

        return new LoginUser(user, perms);
    }
}
