package com.huawei.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author king
 * @date 2022/3/3-23:40
 * @Desc
 */
public class PatternDemo {

    private static final Pattern PATTERN = Pattern.compile("<\\?.*\\?>[\r\n]+");

    public static void main(String[] args) {
        String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<huawei/>";
        Matcher matcher = PATTERN.matcher(str);
        if (matcher.find()) {
            str = matcher.replaceFirst("");
        }
        System.out.println("str = " + str);
    }
}
