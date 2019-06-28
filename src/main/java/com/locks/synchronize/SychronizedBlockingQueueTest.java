package com.locks.synchronize;


import java.util.LinkedList;
import java.util.Queue;

/**
 * 用wait,notify,notifyAll实现一个生产者/消费者模型
 * @see <a>https://cloud.tencent.com/developer/article/1369183</a>
 * @author weishi8
 * @create 2019-05-23
 * @description
 */
public class SychronizedBlockingQueueTest<T> {
    private Queue<T> mQueue = new LinkedList<>();
    private int mCapacity;

    public SychronizedBlockingQueueTest(int capacity) {
        this.mCapacity = capacity;
    }

    public synchronized void put(T element) throws InterruptedException{
        while (mQueue.size() == mCapacity){
            wait();
        }
        mQueue.add(element);
        notify();
    }

    public synchronized T take() throws InterruptedException{
        while (mQueue.isEmpty()){
            wait();
        }
        T item = mQueue.remove();
        notify();
        return item;
    }
}
