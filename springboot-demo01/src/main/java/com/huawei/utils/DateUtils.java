package com.huawei.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author king
 * @date 2022/7/23-1:07
 * @Desc
 */
public class DateUtils {

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat();

    public static String date2String(Date date, String format) {
        SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(format);
        return SIMPLE_DATE_FORMAT.format(date);
    }
}
