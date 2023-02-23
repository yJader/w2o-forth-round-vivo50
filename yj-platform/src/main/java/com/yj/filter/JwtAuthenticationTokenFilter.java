package com.yj.filter;

import com.alibaba.fastjson.JSON;
import com.yj.constants.RedisKeyConstants;
import com.yj.domain.ResponseResult;
import com.yj.domain.entity.LoginUser;
import com.yj.enums.AppHttpCodeEnum;
import com.yj.utils.JwtUtil;
import com.yj.utils.RedisCache;
import com.yj.utils.WebUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @Description:
 * @Package com.yj.filter
 * @Author yJade
 * @Date 2023-02-18 22:26
 */
/**
 * @description: jwt认证过滤器
 * @author: YJader
 * @date: 2023/2/18 22:27
 */
@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取请求头中的token
        // TODO 配合前端将请求头从"token"改为"Authorization"
        String token = request.getHeader("token");
        log.info("请求路径:{}", request.getRequestURI());
        log.info("获取到token:{}", token);
        if(!StringUtils.hasText(token)) {
            //请求头未携带token 说明该接口不需要登录 直接放行
            filterChain.doFilter(request, response);
            return;
        }
        //解析 获取userid
        Claims claims = null;
        try {
            claims = JwtUtil.parseJWT(token);
        } catch (Exception e) {
            e.printStackTrace();
            //token超时 / token非法
            //响应告诉前端 需要重新登录
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(response, JSON.toJSONString(result));
            return;
        }
        String userId = claims.getSubject();

        //从redis中获取用户信息
        LoginUser loginUser = redisCache.getCacheObject(RedisKeyConstants.PLATFORM_LOGIN + userId);
        //如果获取不到
        if(Objects.isNull(loginUser)) {
            //说明登录过期 提示重新登录
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(response, JSON.toJSONString(result));
            return;
        }

        //将用户信息存入SecurityContextHolder
        // TODO 增加权限
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //放行
        filterChain.doFilter(request, response);
    }
}
