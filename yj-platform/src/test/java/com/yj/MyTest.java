package com.yj;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

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

    @Test
    public void testCompare() {
        ArrayList<Integer> integers = new ArrayList<>(Arrays.asList(1, 3, 2, 4));
        integers.sort(null);
        System.out.println(integers);
    }
}
