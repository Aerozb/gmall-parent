package com.yhy.gmall.admin.aop;

import com.yhy.gmall.to.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Slf4j
@Aspect
@Component
public class DataVaildAspect {

    @Pointcut("execution(* com.yhy.gmall.admin..*Controller.*(..))")
    public void pointCutController(){}

    /**
     *  只要控制类方法参数带有BindingResult，就进行参数合法检查，抛出友好异常
     *  目标方法的异常，一般都需要再次抛出去，让全局控制层异常处理类感知，否则
     */
    @Around("pointCutController()")
    public Object validAround(ProceedingJoinPoint joinPoint){
        Object proceed =null;
        try {
            Object[] args = joinPoint.getArgs();
            for (Object arg : args) {
                if (arg instanceof BindingResult){
                    BindingResult bindingResult = (BindingResult) arg;
                    if (bindingResult.getErrorCount()>0){
                        return new CommonResult().validateFailed(bindingResult);
                    }
                }
            }
            proceed = joinPoint.proceed();
            log.debug("校验切面将目标方法已经放行....{}",proceed);
        } catch (Throwable throwable) {
            //因为有带BindingResult，方法还会执行，如果出现其他异常
            // 不抛出这个异常Throwable，全局控制层异常不会感知到
            //如果不写这个抛出异常语句，那么最会后执行下面返回语句，返回空
            throw new RuntimeException(throwable);
        }
        return proceed;
    }
}