package com.yj.exception;

import com.yj.enums.AppHttpCodeEnum;
/**
 * @description: 自定义异常类
 * @author: YJader
 * @date: 2023/2/18 15:58
 */
public class SystemException extends RuntimeException{

    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public SystemException(AppHttpCodeEnum httpCodeEnum) {
        super(httpCodeEnum.getMsg());
        this.code = httpCodeEnum.getCode();
        this.msg = httpCodeEnum.getMsg();
    }
    
}