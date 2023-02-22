package com.yj.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author yJade
 * @Date 2023-02-10 0:06
 * @Package com.yj.utils
 * @Description:
 */
public class BeanCopyUtils {
    public BeanCopyUtils() {
    }

    public static <V> V copyBean(Object source, Class<V> clazz) {
        //创建目标对象
        V result = null;
        try {
            result = clazz.newInstance();
            //实现属性的copy
            BeanUtils.copyProperties(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static <O, V> List<V> copyBeanList(List<O> list, Class<V> clazz) {
        return list.stream()
                .map(o->copyBean(o, clazz))
                .collect(Collectors.toList());
    }
}
