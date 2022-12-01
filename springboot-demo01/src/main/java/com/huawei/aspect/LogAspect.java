package com.huawei.aspect;

import cn.hutool.core.thread.NamedThreadFactory;
import com.huawei.annotation.RecordLog;
import com.huawei.convert.IConvert;
import com.huawei.po.OperatorLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author king
 * @date 2022/11/21-23:12
 * @Desc
 */
@Aspect
@Component
public class LogAspect {

    @Pointcut(value = "@annotation(com.huawei.annotation.RecordLog)")
    public void pointcut() {
    }

    public static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            1,
            1, 60,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(100),
            new NamedThreadFactory("RecordThread-", false),
            new ThreadPoolExecutor.AbortPolicy());

    @Around(value = "pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object obj = proceedingJoinPoint.proceed();
        // 方法执行后，记录日志
        threadPoolExecutor.submit(() -> {
            try {
                MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
                Method method = signature.getMethod();
                RecordLog recordLog = method.getAnnotation(RecordLog.class);
//            OperatorLog operatorLog = new OperatorLog();
//            operatorLog.setDesc(recordLog.value());
//            operatorLog.setResult(obj);
                IConvert iConvert = recordLog.clazz().newInstance();
                OperatorLog operatorLog = iConvert.convert(proceedingJoinPoint.getArgs()[0]);
                operatorLog.setDesc(recordLog.value());
                operatorLog.setResult(obj);

            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }

        });
        return obj;
    }
}
