package com.fortest.interfaces;


/**
 * @author weishi8
 * @create 2019-05-20
 * @description
 */
public interface MyInterfaces {
    public static  final String a ="111";
}

class MyInstatancesClass implements MyInterfaces{
    public static void main(String[] args) {
        MyInterfaces mi = new MyInstatancesClass();

        System.out.println(mi instanceof Object);
    }
}