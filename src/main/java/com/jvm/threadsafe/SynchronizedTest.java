package com.jvm.threadsafe;

/**
 * @author weishi8
 * @create 2019-05-05
 * @description
 */
public class SynchronizedTest {

    public static int i = 0;

    public static void main(String[] args) {

        for(int m=0;m<2000;m++){
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (SynchronizedTest.class){
                        for(int j=0;j<1000;j++){
                            i++;
                        }
                    }
                }
            });
            t.start();
        }

        System.out.println("result:"+i);
    }
}
