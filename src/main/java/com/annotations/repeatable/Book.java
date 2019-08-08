package com.annotations.repeatable;

import java.lang.annotation.Annotation;
import java.util.Arrays;

/**
 * 调用可重复注解示例：
 * 自定义的@Author注解，使用了@Repeatable注解，因此@Author注解可以重复使用
 */
@Author(name="张三")
@Author(name="李四")
@Author(name="王五")
public class Book {

    public static void main(String[] args) {
        Author[] authors = Book.class.getAnnotationsByType(Author.class);
        Arrays.stream(authors).forEach(t->{
                //打印注解的内容
                System.out.println(t.name());
            }
        );
    }
}
