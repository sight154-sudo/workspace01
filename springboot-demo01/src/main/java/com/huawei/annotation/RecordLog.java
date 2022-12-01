package com.huawei.annotation;

import com.huawei.convert.IConvert;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author king
 * @date 2022/11/21-23:10
 * @Desc
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface RecordLog {
    String value() default "";

    Class<? extends IConvert> clazz();
}
