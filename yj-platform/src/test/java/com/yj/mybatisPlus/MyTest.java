package com.yj.mybatisPlus;

import com.yj.domain.entity.User;
import com.yj.mapper.UserMapper;
import com.yj.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    UserMapper userMapper;
    @Test
    public void testSave() {
        User user = new User();
        user.setNickName("1");
        user.setUserName("2");
        user.setPassword("3");
        userService.save(user);
        System.out.println(user);
    }

    @Test
    public void testUpdateBatchById() {
        User user1 = new User(1L, 100);
        User user2 = new User(2L, 100);
        User user3 = new User(3L, 100);
        User user4 = new User(4L, 100);
        List<User> userList = new ArrayList<>(Arrays.asList(user1, user2, user3, user4));
        userService.updateBatchById(userList);
    }

    @Test
    public void testUpdateById() {
        userService.updateById(new User(3L, 123));
    }
}
