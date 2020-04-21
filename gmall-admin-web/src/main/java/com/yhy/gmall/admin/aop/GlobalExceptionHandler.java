package com.yhy.gmall.admin.aop;

import com.yhy.gmall.to.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {NullPointerException.class})
    public Object nullPointerException(Exception e){
        log.error("空指针异常：{}",e.getStackTrace());
        return new CommonResult().validateFailed("空指针异常");
    }
}