package com.annotations.mytest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解测试：作用于包路径上的注解。
 * ElementType.PACKAGE 说明此注解只能作用于包路径上，（即package-info.java)
 */
@Target(ElementType.PACKAGE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PackageAnnotation {
    //包注解
    boolean value() default false;
}
