

package com.annotations.mytest;

import org.springframework.context.annotation.Description;

import java.lang.annotation.*;

/**
 * 自定义注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.PARAMETER,ElementType.TYPE_USE,ElementType.TYPE_PARAMETER})
@Documented
public @interface AnnotationTest {

    //姓名
    String name() default "";
    //年龄
    int age() ;
}
