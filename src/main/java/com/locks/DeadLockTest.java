package com.locks;

/**
 * @author weishi8
 * @create 2019-05-14
 * @description 死锁示例
 */
public class DeadLockTest {

    public static void main(String[] args) {
        Thread1 t1 = new Thread1();
        Thread2 t2 = new Thread2();
        Thread t11 = new Thread(t1,"A");
        Thread t21 = new Thread(t2,"B");
        t11.start();
        t21.start();
    }
}

class Lock {
public static Object obj1 = new Object();
public static Object obj2 = new Object();
}

class Thread1 implements Runnable{
    @Override
    public void run() {
        synchronized (Lock.obj1){
            System.out.println(System.currentTimeMillis()+","+Thread.currentThread().getName()+",获取到锁obj1");
            //通过循环调用，不释放锁资源，来制造机会
            while(true){
                    synchronized (Lock.obj2){
                        System.out.println(System.currentTimeMillis()+","+Thread.currentThread().getName()+",获取到锁obj2");
                    }
            }
        }

    }
}

class Thread2 implements Runnable{
    @Override
    public void run() {
        synchronized (Lock.obj2){
            System.out.println(System.currentTimeMillis()+","+Thread.currentThread().getName()+",获取到锁obj2");
            while (true){
                synchronized (Lock.obj1){
                    System.out.println(System.currentTimeMillis()+","+Thread.currentThread().getName()+",获取到锁obj1");
                }
            }
        }

    }
}