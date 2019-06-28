package com.study.thread.create;

/**
 * @author weishi8
 * @create 2019-04-24
 * @description
 */
 /**
     *@functon 多线程学习
     *@author 林炳文
     *@time 2015.3.9
     */
    class Thread1 extends Thread{
        private String name;
        public Thread1(String name) {
            this.name=name;
        }
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println(name + "运行  :  " + i);
                try {
                    sleep((int) Math.random() * 10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
     class Main {

        public static void main(String[] args) {
            Thread1 mTh1=new Thread1("A");
            Thread1 mTh2=new Thread1("B");
            mTh1.start();
            mTh2.start();

        }

    }

