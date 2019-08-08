package com.annotations.mytest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 适用于构造方法的注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AnnotationConstructTest {
    //方法返回类型
    Class returnType();
    //方法参数个数
    int paramCount() default 0;
}
