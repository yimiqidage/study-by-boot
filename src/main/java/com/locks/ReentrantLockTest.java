package com.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author weishi8
 * @create 2019-05-22
 * @description
 */
public class ReentrantLockTest {

    public static int a = 0;

    public static int b = 0;

    public static void inc(){
        a++;
    }
    public static void incb(){
        b++;
    }

    /**
     * 非线程安全的实现方式
     */
    public static void unsafe(){
        Runnable r = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    inc();
                }
            }
        };

        Thread t1 = new Thread(r,"A");
        Thread t2 = new Thread(r,"B");
        t1.start();
        t2.start();
        try {
            //休眠500毫秒，确认线程已经执行完毕
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用ReentrantLock实现线程安全
     */
    public static void safeByReentrantLock(){
        Lock lock = new ReentrantLock();
        Condition con1 = lock.newCondition();

        Runnable r = new Runnable() {
            @Override
            public void run() {
                try{
                    lock.lock();
                    for (int i = 0; i < 10000; i++) {
                        incb();
                    }
                }finally {
                    lock.unlock();
                }

            }
        };

        Thread t1 = new Thread(r,"A");
        Thread t2 = new Thread(r,"B");
        t1.start();
        t2.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        unsafe();
        System.out.println("非线程安全-计算结果，a:"+a);
        safeByReentrantLock();
        System.out.println("线程安全-计算结果，b:"+b);
    }


}
