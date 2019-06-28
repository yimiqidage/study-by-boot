package com.test.thread.resource;
/**
 * 
 * @author shiwei 2013-3-21 <br/>
 * 经典的多生产者，多消费者模式。
 *
 */

class Food{
	private String name;
	private int count=1;
	private boolean flag = false;
	public synchronized void setParam(String name){
		
		while(this.flag){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		this.name = name + count;
		this.count++;
		this.flag=true;
		
		System.out.println(this.name+"....生产者...........");	
		
		this.notifyAll();
		
	}
	
	public synchronized void out(){
		
		while(!this.flag){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(this.name+"............消费者....................");	
		
		this.flag=false;
		
		this.notifyAll();
	}
	
	
}

class Producer implements Runnable{
	Food r;
	Producer (Food r){
		this.r=r;
	}
	
	public void run(){
		
		while(true){
			r.setParam("烤鸭");
		}
	}
}


class Consumer implements Runnable{
	Food r ;
	
	Consumer (Food r){
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
 * 相对于ProducerConsumerDemoFail.java 的改进，有两点：
 * 		①：将判断的if(this.flag){} 语句，修改成 while(this.flag){} 语句。
 * 		②：将notify() 方法，修改为 notifyAll() 方法。
 * 关于wait() 方法的补充：例如下列代码：
 * 		try {
				this.wait();	//线程进入休眠---
			} catch (InterruptedException e) {	|
				e.printStackTrace();			|
			}									|
			this.name = name + count;<-----------		//线程休眠结束以后，会继续向下执行，不会再判断flag了。在哪休眠的，就在哪醒。所以，如果是if语句，就会继续向下执行了，丧失了flag的约束
			this.count++;
			this.flag=true;
			
 * 
 * 
 * 总结：
 *	if判断标记，只有一次，会导致不该运行的线程运行了。出现了数据错误的情况。
 *	while判断标记，解决了线程获取执行权后，是否要运行！
 *	notify:只能唤醒一个线程，如果本方唤醒了本方，没有意义。而且while判断标记+notify会导致死锁。
 *  notifyAll解决了本方线程一定会唤醒对方线程的问题。
 */
public class ProducerConsumerDemo {

	public static void main(String[] args) {
		Food r = new Food();
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
