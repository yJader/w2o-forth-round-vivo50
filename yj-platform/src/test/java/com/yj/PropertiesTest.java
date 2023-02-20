package com.yj;

import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Package com.yj
 * @Author yJade
 * @Date 2023-02-20 21:00
 */
@SpringBootTest
@Component
@PropertySource("classpath:mykey.properties")
@ConfigurationProperties(prefix = "test")
public class PropertiesTest {
    private String abc;

    public void setAbc(String abc) {
        this.abc = abc;
    }

    @Test
    public void testAbc() {
        System.out.println(abc);
    }
}
