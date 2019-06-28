package com.jvm.bookdemo.z7;

/**
 * 被动使用类字段演示三：
 * 常量在编译阶段会存入调用类的常量池中，本质上没有直接引用到定义常量的类，因此不会触发定义常量的类的初始化。
 **/
public class ConstClass {

    static {
        System.out.println("ConstClass init!");
    }
    // 同SuperClass类的区别在于，使用了final进行修饰；
    public static final String HELLOWORLD = "hello world";
}

/**
 * 非主动使用类字段演示
 **/
class NotInitialization3 {

    public static void main(String[] args) {
        //引用的常量ConstClass.HELLOWORLD，在编译阶段就存入了调用类[NotInitialization3]的常量池
        System.out.println(ConstClass.HELLOWORLD);
    }
}

