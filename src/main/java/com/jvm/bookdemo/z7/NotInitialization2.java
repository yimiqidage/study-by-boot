package com.jvm.bookdemo.z7;

/**
 * 被动使用类字段演示二：
 * 通过数组定义来引用类[SuperClass]，不会触发此类的初始化
 **/
public class NotInitialization2 {

    public static void main(String[] args) {
        //定义SuperClass的数组，不会触发SuperClass的初始化；
        //打印结果没有"SuperClass init!"
        SuperClass[] sca = new SuperClass[10];
    }

}

