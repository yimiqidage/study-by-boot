package com.jvm.bookdemo.z12;


/**
 * volatile变量自增运算测试
 * 解释volatile的线程安全，是什么意思：volatile只是各个线程在获取主内存的值时，可以保证最新，但是对参数的处理动作，并不是原子性的，无法保证各个
 * 线程在获取参数后，修改动作也是线程安全的。
 * volatile可见性：当一条线程修改了这个变量的值，新值对于其他线程来说是可以立即得知的；
 * java里面的运算并非原子操作，导致volatile变量的运算在并发情况下一样是不安全的。
 *
 * @author zzm
 */
public class VolatileTest {

    public static volatile int race = 0;

    public static void increase() {
        race++;
    }

    private static final int THREADS_COUNT = 20;

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        increase();
                    }
                }
            });
            threads[i].start();
        }

        // 等待所有累加线程都结束
        while (Thread.activeCount() > 1)
            Thread.yield();

        System.out.println(race);
    }
}

