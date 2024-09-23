package com.huawei.utils;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import org.springframework.util.ReflectionUtils;

/**
 * @author king
 * @date 2024/1/17-23:23
 * @Desc
 */
public class MyReflectionUtils {

    public static <T> T resolveObj(String expr, Class<?> clazz) {
        if (!expr.startsWith("#")) {
            return null;
        }
        HttpRequest request = HttpRequest.post("");
        ExcelReader excelReader = ExcelUtil.getReader("");
        return null;
    }

}
