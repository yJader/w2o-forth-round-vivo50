package com.yj.job;

import com.yj.constants.RedisKeyConstants;
import com.yj.domain.entity.User;
import com.yj.service.UserService;
import com.yj.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Package com.yj.job
 * @Author yJade
 * @Date 2023-02-16 23:58
 */
@Component
public class UpdatePointsJob {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private UserService userService;

//    @Scheduled(cron = "0/5 * * * * ?") //测试用 5s更新一次
    @SuppressWarnings("AlibabaCommentsMustBeJavadocFormat")
    @Scheduled(cron = "0 0 0/1 * * ? ")
    public void viewCountJob(){
        Map<String, Integer> pointsMap = redisCache.getCacheMap(RedisKeyConstants.USER_POINTS);
        Map<String, Integer> cumulativePointsMap = redisCache.getCacheMap(RedisKeyConstants.USER_CUMULATIVE_POINTS);

        List<User> userList = pointsMap.entrySet().stream()
                .map(entry -> new User(Long.valueOf(entry.getKey()), entry.getValue()))
                .collect(Collectors.toList());
        for (User user : userList) {
            user.setCumulativePoints(cumulativePointsMap.get(user.getId().toString()));
        }


        //更新到数据库中
        //使用mybatis plus封装好的service方法来更新数据
        userService.updateBatchById(userList);
    }
}
