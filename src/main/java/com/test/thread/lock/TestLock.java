package com.test.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示lock的使用方式。
 * @author s
 *
 */


class Resource{
	private String name;
	private int count=1;
	private boolean flag = false;
	final Lock lock = new ReentrantLock();
	final Condition pro_lock = lock.newCondition(); 
	final Condition con_lock = lock.newCondition();
	
	
	public void setParam(String name){
		
		lock.lock();
		try{
			while(this.flag){
				try {
					pro_lock.await();////this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			this.name = name + count;
			this.count++;
			this.flag=true;
			
			System.out.println(this.name+"....生产者...........");	
			
			con_lock.signal();//this.notifyAll();
		}finally{
			lock.unlock();
		}
		
	}
	
	public void out(){
		
		lock.lock();
		try{
			while(!this.flag){
				try {
					con_lock.await();//this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(this.name+"............消费者....................");	
			
			this.flag=false;
			
			pro_lock.signal();//this.notifyAll();
		}finally{
			lock.unlock();
		}
		
		
		
	}
	
	
}

class Producer implements Runnable{
	Resource r;
	Producer (Resource r){
		this.r=r;
	}
	
	public void run(){
		
		while(true){
			r.setParam("烤鸭");
		}
	}
}


class Consumer implements Runnable{
	Resource r ;
	
	Consumer (Resource r){
		this.r=r;
	}
	
	public void run (){
		while(true){
			r.out();
		}
	}
}
/**
 * @author shiwei 2013-3-21 <br/>
 * 相对于ProducerConsumerDemo.java ，使用jdk1.5以后的新加入的Lock，使用步骤：
 * 		①：创建锁对象，使用new ReentrantLock(); 方式。
 * 		②：可以创建多个监视器对象。【注：在以前的synchronized语句块中，一个锁只能有一个监视器对象。】使用lock.newCondition();
 * 		③：针对生产者、消费者，分别创建一个监视器对象。
 * 		④：开启锁，使用lock.lock()方法。
 * 		⑤：可以唤醒指定监视器的对象。【如：一旦生产者生产完毕以后，指定唤醒消费者的对象。t2,或者t3】--->con_lock.signal()
 * 		⑥：执行完毕以后，释放锁，使用lock.unlock()方法。
 *	替换方法：
 *		obj.wait()       ----> condition.await();
 *		obj.notify()     ----> condition.signal();
 *		obj.notifyAll()  ----> condition.signalAll();
 */
public class TestLock {

	public static void main(String[] args) {
		Resource r = new Resource();
		Producer pro  = new Producer(r);
		Consumer con = new Consumer(r);
		Thread t0 = new Thread(pro);
		Thread t1 = new Thread(pro);
		Thread t2 = new Thread(con);
		Thread t3 = new Thread(con);
		t0.start();
		t1.start();
		t2.start();
		t3.start();
	}
	
}


