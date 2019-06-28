package com.jvm.bookdemo.z7;

/**
 * 被动使用类字段演示一：
 * 通过子类[SubClass]引用父类[SuperClass]的静态字段[value]，不会导致子类初始化
 **/
public class SuperClass {

    //1、定义一个父类的静态代码块，如果此类被初始化，那么一定执行此处代码块
    static {
        System.out.println("SuperClass init!");
    }
    //2、定义一个静态常量
    public static int value = 123;
}

 class SubClass extends SuperClass {

    //3、定义子类的静态代码块
    static {
        System.out.println("SubClass init!");
    }

}

/**
 * 非主动使用类字段演示
 **/
 class NotInitialization {

    public static void main(String[] args) {
        //4、通过子类[SubClass]，引用父类[SuperClass]的静态变量[value]
        //5、打印结果只有父类[SuperClass]的初始化，并没有子类[SubClass]的初始化；
        //结论：通过子类引用父类的静态字段，只会对父类进行初始化，不会对子类进行初始化
        System.out.println(SubClass.value);
    }

}
