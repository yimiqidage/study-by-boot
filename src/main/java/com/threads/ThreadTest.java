package com.threads;


import java.util.Random;

/**
 * @author weishi8
 * @create 2019-05-13
 * @description this.getName 与 Thread.currentThread().getName() 区别
 */
public class ThreadTest extends Thread {
    private int count = 5;
    ThreadTest(){
        this.setName("ThreadTest-aaa");
    }
    @Override
    public void run() {
        super.run();

        try{
            int t = (int)Math.random()*1000;
            Thread.sleep(t);
        }catch (Exception e) {
            e.printStackTrace();
        }
        count--;
//        System.out.println(Thread.currentThread().getName()+" run ,count = "+count);
        System.out.println("Thread.currentThread().getName():"+Thread.currentThread().getName());
        System.out.println("this.getName():"+this.getName());
        System.out.println("Thread.currentThread()==this:"+(Thread.currentThread()==this));
    }
//    ThreadTest(String name){
//        this.threadName = name;
//    }
}

class ThreadTestMain {
    public static void main(String[] args) {
        ThreadTest t = new ThreadTest();
        Thread  t1 = new Thread(t,"A");
        Thread  t2 = new Thread(t,"B");
        Thread  t3 = new Thread(t,"C");
        Thread  t4 = new Thread(t,"D");
        Thread  t5 = new Thread(t,"E");
        t1.start();
        t2.start();
//        t3.start();
//        t4.start();
//        t5.start();
    }
}