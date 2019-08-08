package com.annotations.aliasfor;

import org.springframework.core.annotation.AnnotationUtils;

@AliasForTest(name = "11",path = "22")
public class TestAliasForAnnotation {

    public static void main(String[] args) {

        //1.使用jdk获取注解
       AliasForTest aliasForTest =  TestAliasForAnnotation.class.getAnnotation(AliasForTest.class);
       String name = aliasForTest.name();
       String path = aliasForTest.path();
       //可以正常运行，因为AliasFor是Spring定义的注解，jdk并不会进行相关含义校验
       System.out.println("name:"+name+",path:"+path);

       //2.使用Spring工具类获取注解
       AliasForTest aliasForTest2 = AnnotationUtils.findAnnotation(TestAliasForAnnotation.class,AliasForTest.class);
       //会报错，提示name和path只能设置一个属性
       System.out.println(aliasForTest2.name()+","+aliasForTest2.path());

    }
}
