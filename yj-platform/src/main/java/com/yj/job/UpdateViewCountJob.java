package com.yj.job;

import com.yj.constants.RedisKeyConstants;
import com.yj.domain.entity.Project;
import com.yj.service.ProjectService;
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
public class UpdateViewCountJob {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ProjectService projectService;

//    @Scheduled(cron = "0/5 * * * * ?") //测试用 5s更新一次
    @SuppressWarnings("AlibabaCommentsMustBeJavadocFormat")
    @Scheduled(cron = "0 0/10 * * * ? ")
    public void testJob(){
        //获取redis中的浏览量
        Map<String, Integer> viewCountMap = redisCache.getCacheMap(RedisKeyConstants.PROJECT_VIEW_COUNT);
        List<Project> projectList = viewCountMap.entrySet()
                .stream()
                .map(entry -> new Project(Long.valueOf(entry.getKey()), entry.getValue().longValue()))
                .collect(Collectors.toList());
        //更新到数据库中
        //使用mybatis plus封装好的service方法来更新数据
        projectService.updateBatchById(projectList);
    }
}
