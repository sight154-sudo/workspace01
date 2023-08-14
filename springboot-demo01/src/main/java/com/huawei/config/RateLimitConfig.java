package com.huawei.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RateLimitConfig {

    @Value("${rate.limit.default}")
    private int defaultRateLimit;

    public int getDefaultRateLimit() {
        return defaultRateLimit;
    }
}
