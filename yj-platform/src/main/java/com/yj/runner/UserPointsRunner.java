package com.yj.runner;

import com.yj.constants.RedisKeyConstants;
import com.yj.domain.entity.User;
import com.yj.mapper.UserMapper;
import com.yj.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 浏览量缓存
 * @Package com.yj.runner
 * @Author yJade
 * @Date 2023-02-21 12:48
 */
@Component
public class UserPointsRunner implements CommandLineRunner {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisCache redisCache;

    @Override
    public void run(String... args) throws Exception {
        List<User> users = userMapper.selectList(null);
        Map<String, Integer> userPointMap = new HashMap<>();
        for (User user1 : users) {
            if (userPointMap.put(user1.getId().toString(), user1.getPoints()) != null) {
                throw new IllegalStateException("Duplicate key");
            }
        }
        Map<String, Integer> userCumulativePointMap = new HashMap<>();
        for (User user : users) {
            if (userCumulativePointMap.put(user.getId().toString(), user.getCumulativePoints()) != null) {
                throw new IllegalStateException("Duplicate key");
            }
        }
        redisCache.setCacheMap(RedisKeyConstants.USER_POINTS, userPointMap);
        redisCache.setCacheMap(RedisKeyConstants.USER_CUMULATIVE_POINTS, userCumulativePointMap);
    }
}
