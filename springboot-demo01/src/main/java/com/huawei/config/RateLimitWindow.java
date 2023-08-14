package com.huawei.config;

public class RateLimitWindow {
    private final int maxRequests;
    private final long windowDuration = 1000; // 1秒
    private long windowStart;
    private int requestCount;

    public RateLimitWindow(int maxRequests) {
        this.maxRequests = maxRequests;
        this.windowStart = System.currentTimeMillis();
    }

    public synchronized boolean tryConsume() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - windowStart >= windowDuration) {
            windowStart = currentTime;
            requestCount = 0;
        }

        if (requestCount < maxRequests) {
            requestCount++;
            return true;
        }

        return false;
    }
}
