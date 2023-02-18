package com.yj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Description:
 * @Package com.yj
 * @Author yJade
 * @Date 2023-02-18 1:18
 */
@SpringBootApplication
@MapperScan("com.yj.mapper")
@EnableScheduling
public class PlatformApplication {
    public static void main(String[] args) {
        SpringApplication.run(PlatformApplication.class, args);
    }
}
