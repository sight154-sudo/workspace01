package com.huawei.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author king
 * @date 2024/1/14-15:17
 * @Desc
 */
@Aspect
@Component
public class CustomerAspect {

    public static final String point = "execution(* com.huawei.service.impl.CustomerServiceImpl.query())";

    @Pointcut(point)
    public void pointCut(){}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint process) throws Throwable {
        System.out.println("aaaaaaaa");
        return process.proceed(process.getArgs());
    }
}
