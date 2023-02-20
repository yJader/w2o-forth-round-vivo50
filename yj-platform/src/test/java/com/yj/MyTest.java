package com.yj;

import org.junit.jupiter.api.Test;

/**
 * @Description:
 * @Package com.yj
 * @Author yJade
 * @Date 2023-02-20 22:42
 */
public class MyTest {
    @Test
    public void testString() {
        String s = new String("a");
        System.out.println(s.length());
        s = "一二";
        System.out.println(s.length());
    }
}
