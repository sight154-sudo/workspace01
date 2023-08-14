package com.huawei.aspect;

import com.huawei.annotation.RateLimit;
import com.huawei.config.RateLimitWindow;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
public class RateLimitAspect {
    private Map<String, RateLimitWindow> requestWindows = new ConcurrentHashMap<>();

    @Before("@annotation(rateLimitAnnotation)")
    public void enforceRateLimit(JoinPoint joinPoint, RateLimit rateLimitAnnotation) throws Exception {
        String methodName = joinPoint.getSignature().toLongString();

        RateLimitWindow rateLimitWindow = requestWindows.computeIfAbsent(methodName, k -> new RateLimitWindow(rateLimitAnnotation.value()));
        if (!rateLimitWindow.tryConsume()) {
            throw new Exception("Rate limit exceeded for method: " + methodName);
        }
    }
}
