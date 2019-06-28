package com.jvm.bookdemo.z7;

/**
 * 示例1：引用类的静态字段，会导该类进行初始化;
 * 示例2：虚拟机启动时，执行的主类（main方法所在类），虚拟机会初始化这个类
 */
public class StaticClass {
    static {
        System.out.println("StaticClass init!");
    }
    public static int number = 233;
}

class StaticClassTest{
    static{
        System.out.println("StaticClassTest init!");
    }
    public static void main(String[] args) {
        System.out.println(StaticClass.number);
    }
}