package com.huawei.config;

import com.alibaba.ttl.TransmittableThreadLocal;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author king
 * @date 2022/9/15-22:11
 * @Desc
 */
public class ContextUtils {
    private static TransmittableThreadLocal<Map<String, Object>> threadLocal =
            new TransmittableThreadLocal<Map<String, Object>>() {

            };


    public static void put(String key, Object obj) {
        Map<String, Object> res = threadLocal.get();
        if (Objects.isNull(res)) {
            res = new HashMap<>();
        }
        res.put(key, obj);
        threadLocal.set(res);
    }

    public static Object get(String key) {
        Map<String, Object> map = threadLocal.get();
        return map == null ? map : map.get(key);
    }

    public static void clear() {
        threadLocal.remove();
    }


}
