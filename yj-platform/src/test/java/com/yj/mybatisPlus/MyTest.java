package com.yj.mybatisPlus;

import com.yj.domain.entity.User;
import com.yj.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description:
 * @Package com.yj.mybatisPlus
 * @Author yJade
 * @Date 2023-02-21 22:42
 */
@SpringBootTest
public class MyTest {
    @Autowired
    UserService userService;
    @Test
    public void testSave() {
        User user = new User();
        user.setNickName("1");
        user.setUserName("2");
        user.setPassword("3");
        userService.save(user);
        System.out.println(user);
    }
}
