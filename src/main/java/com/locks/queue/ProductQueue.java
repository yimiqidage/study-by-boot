package com.locks.queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用 condition实现生产者消费者模型，先入先出队列。
 * @param <T>
 */
public class ProductQueue<T> {

    //队列中的内容项
    private final T[] items;
    //锁对象
    private final Lock lock = new ReentrantLock();
    //锁条件
    private Condition notFull = lock.newCondition();
    //锁条件
    private Condition notEmpty = lock.newCondition();

    private Condition thirdConditon = lock.newCondition();

    //头部队列元素
    private int head ;
    //尾部队列元素
    private int tail;
    //队列总长度
    private int count;

    public ProductQueue(int maxSize) {
        items = (T[]) new Object[maxSize];
    }

    public ProductQueue() {
        this(10);
    }

    /**
     * 向队列压入数据
     * @param t
     * @throws InterruptedException
     */
    public void put(T t) throws InterruptedException {
        System.out.println("开始执行put...准备获取lock");
        //1、获取锁对象
        lock.lock();
        System.out.println("开始执行put...获取到lock");
        try {
            //2、如果当前队列已满（已满判断的条件是初始化的容量，已经等于当前队列的长度）
            while (count == getCapacity()) {
                System.out.println("当前队列已满，count="+count);
                //3、通过await，释放当前锁
                notFull.await();
            }
            //4、通过tail下标，设置数组items[tail]的值为put的对象
            items[tail] = t;
            //5.1、先将下标自增1，
            //5.2、如果数组下标tail+1=队列容量，则下标初试化为0，后面的数据则继续会从0开始进行压入；
            if (++tail == getCapacity()) {
                //5.3、下标初试化为0
                tail = 0;
            }
            //6、队列数量，自增1
            ++count;
            //7、通知消费者队列，从await状态中，恢复过来，并重新获取lock锁
            notEmpty.signalAll();
            System.out.println("队列："+t.toString()+",入队");

        } finally {
            lock.unlock();
        }
    }

    /**
     * 从队列中取出数据
     * @return
     * @throws InterruptedException
     */
    public T take() throws InterruptedException {
        System.out.println("开始执行take...准备获取lock");
        //1、获取锁对象
        lock.lock();
        System.out.println("开始执行take...获取到lock");
        try {
            //2、如果队列为空，则释放当前锁，并且当前线程处于无限期等待
            while (count == 0) {
                notEmpty.await();
            }
            //3、从0开始，获取items中的对象T；
            T ret = items[head];
            //4、手动设置获取后的items数组，对象为空；
            items[head] = null;//GC
            //5.1、head自增1；
            //5.2、如果head自增后，等于当前队列容量，则head初始化，继续从0开始
            if (++head == getCapacity()) {
                head = 0;
            }
            //6、消费后，将队列的长度减1（此处因为在获取lock对象以后，才能执行到，所以不存在线程安全问题）
            --count;
            //7、通知生产者队列，可以继续压入数据。
            notFull.signalAll();
            System.out.println("队列："+ret.toString()+",消费");
            return ret;
        } finally {
            lock.unlock();
        }
    }

    public int getCapacity() {
        return items.length;
    }

    public int size() {
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }

}
