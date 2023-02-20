package com.yj.handler.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.yj.utils.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description:
 * @Package com.yj.handler.mybatisplus
 * @Author yJade
 * @Date 2023-02-20 10:05
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        // TODO 添加try catch 否则报错(不知道为什么 出现了再改)
        Long userId = SecurityUtils.getUserId();

        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("createBy", userId, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {

    }
}
