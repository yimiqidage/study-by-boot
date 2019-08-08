package com.annotations.aliasfor;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AliasForTest {

    @AliasFor(value = "path")
    String name() default "";

    @AliasFor(attribute = "name")
    String path() default  "";

}
