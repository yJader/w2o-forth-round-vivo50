package com.yj.aspect;

import com.yj.constants.RedisKeyConstants;
import com.yj.utils.RedisCache;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Package com.yj.aspect
 * @Author yJade
 * @Date 2023-02-21 13:04
 */
@Component
@Aspect
public class ViewCountAspect {

    @Autowired
    private RedisCache redisCache;

    @Pointcut("@annotation(com.yj.annotation.UpdateViewCount)")
    public void pt(){}

    @After("pt()")
    public void afterMethod(JoinPoint joinpoint) {
        Object[] args = joinpoint.getArgs();
        Long id = (Long) args[0];
        redisCache.incrementCacheMapValue(RedisKeyConstants.PROJECT_VIEW_COUNT, id.toString(), 1);
    }
}
