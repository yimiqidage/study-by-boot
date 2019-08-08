package com.annotations.repeatable;

import java.lang.annotation.*;

/**
 * 使用 Repeatable 示例:
 * 在可重复使用的注解上，增加@Repeatable注解
 */
@Repeatable(Authors.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Author {
    String name();
}
