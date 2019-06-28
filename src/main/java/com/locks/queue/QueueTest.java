package com.locks.queue;

/**
 * 测试ProductQueue
 * @author weishi8
 * @create 2019-05-22
 * @description
 */
public class QueueTest {

    public static void main(String[] args) throws InterruptedException {
        ProductQueue<Student> queue  = new ProductQueue<>(3);
        Thread putThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        queue.put(new Student("zhangsan-" + i, i));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread takeThread = new Thread(){
            @Override
            public void run() {
                try {
                    while(true){
                        queue.take();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        putThread.start();
        //休眠0.5秒，保证队列已经有数据了
        Thread.sleep(500);
        takeThread.start();

    }
}
