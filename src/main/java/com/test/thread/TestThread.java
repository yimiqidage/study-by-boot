package com.test.thread;

/**
 * @author shiwei 2013-3-15 <br/>
 * 测试线程:<br/>
 * 		①：注意线程启动的方式是 thread.start()，不是 thread.run() 如果使用run方法，就和普通的函数方法调用，没区别了，不会开启多线程。<br/>
 * 		②：注意开启了多线程，打印的"over" 不一定是最后输出，是同时执行的。<br/>
 * 		③：实现多线程的两种方式：<br/>
 * 					(1):继承Thread类，如：ThreadDemo。<br/>
 * 					(2):实现Runnable接口，通过Thread的有参构造函数来实现，如ThreadDemo2。<br/>
 * 线程的方法：<br/>
 * 		(1):wait()：让线程处于冻结状态，被wait的线程会被存储到线程池中。<br/>
 * 		(2):notify():notify():唤醒线程池中一个线程(任意).---> 注意是任意的。<br/>
 * 		(3):notifyAll()：notifyAll():唤醒线程池中的所有线程。<br/>
 *
 * 这些方法都必须定义在同步中，因为这些方法是用于操作线程状态的方法，必须要明确到底操作的是哪个锁上的线程。<br/>
 * 使用方法参照ResourceDemo2.java ; 如调用的形式 r.notify() 他的含义就是唤醒r锁上的线程，不加可能会乱。<br/>
 * 
 * 为什么操作线程的方法wait notify notifyAll定义在了Object类中？ <br/>
 * 		因为这些方法是监视器的方法，<B>监视器其实就是锁</B>；锁可以是任意的对象，任意的对象调用的方式一定定义在Object类中。<br/>
 *
 *
 */
public class TestThread {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		testUse();
		
	}

	
	/**
	 * 测试两种建立多线程的方式
	 */
	public static void testUse(){
		//①、直接继承Thread
		ThreadDemo d1 = new ThreadDemo("xiaoqiang");
		ThreadDemo d2 = new ThreadDemo("laoqiang");
		d1.start();
		d2.start();
		System.out.println("over:"+Thread.currentThread().getName());
		
		//②、、调用方式：将实现Runnable接口的对象，作为构造函数的参数，传入Thread。
		ThreadDemo2 d = new ThreadDemo2();
		Thread t = new Thread(d);
		t.start();
	}
	
}



