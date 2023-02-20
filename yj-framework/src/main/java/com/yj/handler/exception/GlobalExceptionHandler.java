package com.yj.handler.exception;

import com.yj.domain.ResponseResult;
import com.yj.enums.AppHttpCodeEnum;
import com.yj.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @Description:
 * @Package com.yj.handler.exception
 * @Author yJade
 * @Date 2023-02-18 23:52
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(SystemException.class)
    public ResponseResult systemExceptionHandler(SystemException e) {
        //打印异常信息
        log.error("出现了异常! {}", e);
        //从异常对象中获取提示信息 并封装返回
        return ResponseResult.errorResult(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        //获取异常信息
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();

        StringBuilder errorMessage = new StringBuilder("");

        for (ObjectError error : allErrors) {
            errorMessage.append(error.getDefaultMessage()).append(";");
        }

        //打印异常信息
        log.error("出现了异常!{}", errorMessage);
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(), errorMessage.toString());
    }

    @ExceptionHandler(Exception.class)
    public ResponseResult exceptionHandler(Exception e) {
        //打印异常信息
        log.error("出现了异常:{}", e);
        //从异常对象中获取提示信息 并封装返回
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(), e.getMessage());
    }
}
