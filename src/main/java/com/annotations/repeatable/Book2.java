package com.annotations.repeatable;

import java.util.Arrays;

/**
 * Authors 的另一种写法，直接在Authors中定义多个Author注解对象
 */
@Authors({@Author(name="张三Book2"),@Author(name="李四Book2"),@Author(name="王五Book2")})
public class Book2 {
    public static void main(String[] args) {
        Authors authors = Book2.class.getAnnotation(Authors.class);
        Author[] auArr =authors.value();
        Arrays.asList(auArr).stream().forEach(t->{
            //打印注解的内容
            System.out.println(t.name());
        });

    }
}
