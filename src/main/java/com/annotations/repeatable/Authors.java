package com.annotations.repeatable;


import java.lang.annotation.*;

/**
 * 可重复注解，必须返回组用于@Repeatable注解的类型的数组。
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Authors {

    //Author使用了@Author注解，此处必须返回Author[]数组
    Author[] value();

}
