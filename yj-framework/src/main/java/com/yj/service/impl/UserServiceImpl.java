package com.yj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yj.constants.RedisKeyConstants;
import com.yj.constants.ValueSettingConstants;
import com.yj.domain.ResponseResult;
import com.yj.domain.entity.LoginUser;
import com.yj.domain.entity.Project;
import com.yj.domain.entity.User;
import com.yj.domain.vo.InputPointsVO;
import com.yj.domain.vo.UserInfoVO;
import com.yj.enums.AppHttpCodeEnum;
import com.yj.exception.SystemException;
import com.yj.mapper.ProjectMapper;
import com.yj.mapper.UserMapper;
import com.yj.service.UserService;
import com.yj.utils.BeanCopyUtils;
import com.yj.utils.RedisCache;
import com.yj.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

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

    @Autowired
    private RedisCache redisCache;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public ResponseResult<UserInfoVO> userInfo(Long userId) {
        //根据用户id查询用户信息
        User user = getById(userId);
        user.setPoints(getPoints(userId));
        //封装成UserInfoVO
        UserInfoVO vo = BeanCopyUtils.copyBean(user,UserInfoVO.class);
        return ResponseResult.okResult(vo);
    }

    @Override
    public ResponseResult updateUserInfo(User user) {

        user.setId(SecurityUtils.getUserId());
        //更新
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
        user.setPoints(ValueSettingConstants.INITIAL_POINT);
        //存入数据库
        save(user);

        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult signIn(Long userId) {
        User user = getById(userId);

        Date lastSignInDate1 = user.getLastSignInDate();
        //有可能从未签到过
        if (Objects.nonNull(lastSignInDate1)) {
            LocalDate lastSignInDate = lastSignInDate1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate now = LocalDate.now();

            //有铸币isAfter和isBefore写反了 半天看不出来
            if (now.isAfter(lastSignInDate)) {
                user.setLastSignInDate(new Date());
            } else if (now.isEqual(lastSignInDate)){
                throw new SystemException(AppHttpCodeEnum.REPEATED_SIGN_IN);
            } else {
                throw new SystemException(AppHttpCodeEnum.SIGN_IN_TIME_ERROR);
            }
        } else {
            user.setLastSignInDate(new Date());
        }
        updateById(new User(userId, user.getLastSignInDate()));

        //获取积分 存入redis
        redisCache.incrementCacheMapValue(RedisKeyConstants.USER_POINTS, userId.toString(), ValueSettingConstants.AMOUNT_OF_SIGN_IN_POINTS);
        redisCache.incrementCacheMapValue(RedisKeyConstants.USER_CUMULATIVE_POINTS, userId.toString(), ValueSettingConstants.AMOUNT_OF_SIGN_IN_POINTS);

        return ResponseResult.okResult();
    }

    /**
     * @description: 为众筹项目投入积分
     * @param: targetProjectId
     * @param: points
     * @return: com.yj.domain.ResponseResult
     * @author: YJader
     * @date: 2023/2/23 19:01
     */
    @Override
    @Transactional
    public ResponseResult<InputPointsVO> inputPoints(Long targetProjectId, Integer points) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        User user = loginUser.getUser();
        //要从redis中读取
        user.setPoints(getPoints(user.getId()));

        //校验用户积分是否足够
        if (user.getPoints() < points) {
            throw new SystemException(AppHttpCodeEnum.INSUFFICIENT_POINTS);
        }

        Project project = projectMapper.selectById(targetProjectId);
        //校验项目是否存在
        if (Objects.isNull(project)) {
            throw new SystemException(AppHttpCodeEnum.PROJECT_NOT_EXIST);
        }
        //积分超过项目目标 同样可以继续投入

        //投入积分
        project.setCurrent(project.getCurrent()+points);
        user.setPoints(user.getPoints()-points);
        projectMapper.updateById(project);
        setPoints(user.getId(), user.getPoints());

        return ResponseResult.okResult(new InputPointsVO(points, user.getPoints()));
    }

    private boolean isExist(String variable, SFunction<User, ?> function) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(function, variable);
        return count(queryWrapper) > 0;
    }

    private Integer getPoints(Long userId) {
        Integer points = redisCache.getCacheMapValue(RedisKeyConstants.USER_POINTS, String.valueOf(userId));
        return points;
    }

    private void setPoints(Long userId, Integer points) {
        redisCache.setCacheMapValue(RedisKeyConstants.USER_POINTS, String.valueOf(userId), points);
    }
}

