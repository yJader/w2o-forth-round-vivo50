package com.yj.constants;

import lombok.Data;

/**
 * @Description:
 * @Package com.yj.constants
 * @Author yJade
 * @Date 2023-02-18 21:41
 */
@Data
public class RedisKeyConstants {
    public static final String PLATFORM_LOGIN = "platlogin:";
    public static final String PROJECT_VIEW_COUNT = "project:viewCount";
    public static final String USER_POINTS = "user:points";
    public static final String USER_CUMULATIVE_POINTS = "user:cumulativePoints";
}
