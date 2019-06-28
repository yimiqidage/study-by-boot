package com.jvm.threadsafe;

/**
 * @author weishi8
 * @create 2019-05-05
 * @description
 */
public class SynchronizedTest2 {

    public static int i = 0;

    Thread t = new Thread(new Runnable() {
        @Override
        public void run() {
//            synchronized (SynchronizedTest.class){
            for(int j=0;j<1000;j++){
                i++;
            }
//            }
        }
    });

    public static void main(String[] args) {
        for(int m=0;m<800;m++){
            new SynchronizedTest2().t.start();
        }
        while (Thread.activeCount() > 1)
            Thread.yield();
        System.out.println("result:"+i);
    }
}
