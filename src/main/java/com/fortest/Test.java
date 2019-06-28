package com.fortest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author weishi8
 * @create 2019-04-03
 * @description
 */
public class Test {
    public static void main(String[] args) throws  Exception{
        int a=8;
        int b=3;
        System.out.println("计算结果："+( a & b));
        System.out.println("二进制转换-8："+Integer.toBinaryString(8));
        System.out.println("二进制转换-7："+Integer.toBinaryString(7));
        System.out.println(Integer.parseInt("0028",16));
        System.out.println(Integer.parseInt("d2",32));

        ReentrantLock lock = new ReentrantLock(true);
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();

    }
}
