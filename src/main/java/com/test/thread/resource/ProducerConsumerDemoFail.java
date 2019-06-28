package com.test.thread.resource;
/**
 * 
 * @author shiwei 2013-3-21 <br/>
 * 经典的多生产者，多消费者模式。
 *
 */

class Food_fail{
	private String name;
	private int count=1;
	private boolean flag = false;
	public synchronized void setParam(String name){
		
		if(this.flag){
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
		
		this.notify();
		
	}
	
	public synchronized void out(){
		
		if(!this.flag){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(this.name+"............消费者....................");	
		
		this.flag=false;
		
		this.notify();
	}
	
	
}

class Producer_fail implements Runnable{
	Food_fail r;
	Producer_fail (Food_fail r){
		this.r=r;
	}
	
	public void run(){
		
		while(true){
			r.setParam("烤鸭");
		}
	}
}


class Consumer_fail implements Runnable{
	Food_fail r ;
	
	Consumer_fail (Food_fail r){
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
 * 原来的ResourceDemo2_improved.java 的设置一次值，取一次值的模式，流程上没有修改，只是修改了一下名称，变为
 * 生产者和消费者；并且添加了多个生产者和消费者。
 * 	期望结果：应该是烤鸭被生产一次，然后被消费一次。
 * 	运行结果会出现问题：烤鸭可以连续被生产多次；或者同样一直烤鸭，被消费多次。
 * 	正确的代码，查看ProducerConsumerDemo.java
 * 
 * 
 */
public class ProducerConsumerDemoFail {

	public static void main(String[] args) {
		Food_fail r = new Food_fail();
		Producer_fail pro  = new Producer_fail(r);
		Consumer_fail con = new Consumer_fail(r);
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
