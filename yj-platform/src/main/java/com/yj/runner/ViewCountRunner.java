package com.yj.runner;

import com.yj.constants.RedisKeyConstants;
import com.yj.domain.entity.Project;
import com.yj.mapper.ProjectMapper;
import com.yj.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: 浏览量缓存
 * @Package com.yj.runner
 * @Author yJade
 * @Date 2023-02-21 12:48
 */
@Component
public class ViewCountRunner implements CommandLineRunner {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private RedisCache redisCache;

    @Override
    public void run(String... args) throws Exception {
        List<Project> projects = projectMapper.selectList(null);
        Map<String, Integer> viewCountMap = projects.stream()
                .collect(Collectors.
                        toMap(project -> project.getId().toString()
                                , project -> project.getViewCount().intValue()));
        redisCache.setCacheMap(RedisKeyConstants.PROJECT_VIEW_COUNT, viewCountMap);
    }
}
