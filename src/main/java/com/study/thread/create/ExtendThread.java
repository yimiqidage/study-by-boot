package com.study.thread.create;

/**
 * @author weishi8
 * @create 2019-04-02
 * @description 创建多线程方式：继承Thread类
 */
public class ExtendThread  extends Thread{
    private String threadName;
    private Thread t;
    public ExtendThread (String threadName){
       this.threadName = threadName;
    }

    public void run() {
        for (int i = 0; i <40 ; i++) {
            System.out.println("execute run() - "+ i + ","+threadName);
            try {
                sleep((int) Math.random() * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExtendThread t = new ExtendThread("threadName-1");
        ExtendThread t2 = new ExtendThread("threadName-2");
        t.start();
        t2.start();
    }

}
