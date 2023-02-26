package com.yj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Description:
 * @Package com.yj
 * @Author yJade
 * @Date 2023-02-18 1:18
 */
@SpringBootApplication
@MapperScan("com.yj.mapper")
@EnableScheduling
@EnableTransactionManagement // 开启事务
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class PlatformApplication {
    public static void main(String[] args) {
        SpringApplication.run(PlatformApplication.class, args);
    }
}
