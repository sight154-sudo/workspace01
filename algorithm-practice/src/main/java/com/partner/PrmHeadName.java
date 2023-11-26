package com.partner;

import jdk.nashorn.internal.ir.annotations.Reference;

import javax.annotation.Resource;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author king
 * @date 2023/11/25-12:16
 * @Desc
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PrmHeadName {
    String cnName() default "";
    String enName() default "";
}
