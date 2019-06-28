package com.jvm.bookdemo.z8;

import java.io.Serializable;

/**
 * 方法重载，
 */
public class Overload {

    public static void sayHello(Object arg) {
        System.out.println("hello Object");
    }

    public static void sayHello(int arg) {
        System.out.println("hello int");
    }

    public static void sayHello(long arg) {
        System.out.println("hello long");
    }

    public static void sayHello(Character arg) {
        System.out.println("hello Character");
    }

    public static void sayHello(char arg) {
        System.out.println("hello char");
    }

    public static void sayHello(char... arg) {
        System.out.println("hello char ...");
    }

    public static void sayHello(Serializable arg) {
        System.out.println("hello Serializable");
    }

    /**
     * 方法重载优先级：char->int->long->floag->double
     * Character->Object
     * @param args
     */
    public static void main(String[] args) {
        //字符'a'，除了可以代表字符串a之外，还可以代表
        sayHello('a');
    }
}

