package com.test.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @author shiwei 2013-3-21 <br/>
 * API文档中的实现：Condition.java ---->BoundedBuffer
 *
 */
class BoundedBuffer {
	   final Lock lock = new ReentrantLock();
	   final Condition notFull  = lock.newCondition(); 
	   final Condition notEmpty = lock.newCondition(); 

	   final Object[] items = new Object[100];
	   int putptr, takeptr, count;

	   public void put(String x) throws InterruptedException {
	     lock.lock();
	     try {
	       while (count == items.length) 
	         notFull.await();
	       items[putptr] = x; 
	       if (++putptr == items.length) putptr = 0;
	       ++count;
	       notEmpty.signal();
	     } finally {
	       lock.unlock();
	     }
	   }

	   public Object take() throws InterruptedException {
	     lock.lock();
	     try {
	       while (count == 0) 
	         notEmpty.await();
	       Object x = items[takeptr]; 
	       if (++takeptr == items.length) takeptr = 0;
	       --count;
	       notFull.signal();
	       return x;
	     } finally {
	       lock.unlock();
	     }
	   } 
	 }


class PutThread implements Runnable{
	BoundedBuffer b;
	
	PutThread (BoundedBuffer b){
		this.b=b;
	}
	public void run(){
		String x= "烤鸭";
		while(true){
			try {
				b.put(x);
				System.out.println("put:"+x);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}

class TakeThread implements Runnable{
	
	BoundedBuffer b;
	
	TakeThread (BoundedBuffer b){
		this.b=b;
	}
	
	public void run(){
		while(true){
			try {
				b.take();
				System.out.println("take:");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
class demo{
	public static void main(String[] args) {
		BoundedBuffer b = new BoundedBuffer();
		PutThread p = new PutThread(b);
		TakeThread t = new TakeThread(b);
		Thread t0 = new Thread(p);
		Thread t1 = new Thread(p);
		Thread t2 = new Thread(t);
		Thread t3 = new Thread(t);
		t0.start();
		t1.start();
		t2.start();
		t3.start();
	}
	
}
