package com.huawei;

import lombok.Data;
import org.springframework.core.annotation.AnnotatedElementUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author king
 * @date 2022/8/30-0:24
 * @Desc
 */
public class Cache {

    private String key;
    private Object value;
    // 是否过期
    private boolean expire;
    // 过期时间
    private long timeout = 3000L;

    private Map<String, CacheObject> cacheObjectMap = new ConcurrentHashMap<>();

    public Cache() {
        this.timeout = this.timeout + System.currentTimeMillis();
    }

    public Cache(String key, Object value, boolean expire, long timeout) {
        this.key = key;
        this.value = value;
        this.expire = expire;
        this.timeout = timeout + System.currentTimeMillis();
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout < 0?System.currentTimeMillis() : System.currentTimeMillis()+timeout;
    }

    public void set(String key, Object obj) {
        CacheObject cacheObject = new CacheObject(key, obj);
        cacheObjectMap.put(key, cacheObject);
    }

    public Object get(String key) {
        long now = System.currentTimeMillis();
        CacheObject cacheObject = cacheObjectMap.get(key);
        if (cacheObject != null) {
            Long timestamp = cacheObject.getTimestamp();
            if (timestamp - now < timeout) {
                return cacheObject.getValue();
            }
        }
        return null;
    }

    @Data
    private static class CacheObject {
        private String key;
        private Object value;
        private long timestamp;

        public CacheObject(String key, Object value) {
            this.key = key;
            this.value = value;
            this.timestamp = System.currentTimeMillis();
        }
    }
}
