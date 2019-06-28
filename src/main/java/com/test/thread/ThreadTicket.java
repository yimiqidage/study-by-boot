package com.test.thread;

/**
 * @author shiwei 2013-3-19 <br/>
 *	买票示例：<br/>
 *		4个线程，同时卖100张票。参照方法：success()。<br/>
 *		实现方式一： ①：类ThreadTicket继承Runnable接口，<br/>
 *			        ②：然后创建类ThreadTicket的一个对象，然后用这个对象分别创建4个线程。<br/>
 *				    ③：分别开启这四个线程。<br/>
 *		实现方式二：对于ThreadTicketError类，只需要将变量num改成静态变量，同样可以达到相同的效果。<br/>
 *					原因：静态变量在类进行初始化的时候，就已经创建了，并且，创建类的对象的时候，是不会存储静态变量的。<br/>
 *							这样，创建的4个线程对象ThreadTicketError使用的仍然是同一个num。<br/>
 *
 *		分析：如果不是实现Runnable接口，而是继承Thread，则做法也是new4个ticket对象，创建四个线程。参照方法：error()<br/>
 * 
 * 线程安全产生问题的原因：多个线程操作共享的数据。<br/>
 * 如上面的买票示例，就有可能出现卖出错误票的可能。（可以在num--的语句上面一条，休眠一段时间。）<br/>
 * 解决办法：使用 synchronized语句块： synchronized(obj){} ,对象可以使用任意。 或者使用 this ，或者使用ThreadTicket.class 都可以，只要保证多个线程使用的是同一个对象就行。<br/>
 * 			注意：如果使用synchronized(new Object){} 则不会起到效果，因为每个线程调用的时候，都是同步的一个新的Object。应该使用synchronized(obj){}<br/>
 */
public class ThreadTicket implements Runnable {

	private int num = 100;
	Object obj = new Object();
	
	public void run(){
		while(true){
			
			synchronized (obj) { //注意同步代码语句块，需要传入一个对象。
								 // Error代码：synchronized ( new Object()) {
				
				if(num>0){
					
					//添加休眠代码以后，就有可能出现卖的错票，如：ticket:Thread-2:-3
					try{
						Thread.sleep(10);
					}catch(Exception e){
						e.printStackTrace();
					}
					
					num--;
					System.out.println("ticket:"+Thread.currentThread().getName()+":"+num);
				}else {
					break;
				}
			}
			
		}
	}
	
	/**
	 * 静态同步函数，使用ThreadTicket.class为锁，视频[JavaSE基础视频13\20-多线程(验证静态同步函数的锁).avi]
	 */
	public static synchronized  void show(){
		
		
	}
	
	/**
	 * 非静态同步函数，使用的是this为锁，视频[JavaSE基础视频13\20-多线程(验证静态同步函数的锁).avi]
	 */
	public synchronized void show2(){
		
	}
	public static void main(String[] args) {
		success();
		System.out.println("-------------");
//		error();
	}

	/**
	 * 正确的实现方式：实现Runnable接口，并使用同一个对象，创建4个线程。
	 * 只new了一个对象，用这一个对象创建了4个线程，则更新的是同一个num。
	 */
	public static void success() {
		ThreadTicket t = new ThreadTicket();
		Thread t1 = new Thread(t);
		Thread t2 = new Thread(t);
		Thread t3 = new Thread(t);
		Thread t4 = new Thread(t);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
	
	/**
	 * 错误的实现方式：继承Thread，并创建4个不同的线程对象，并开启线程。
	 * 错误原因：类的变量num ，相当于在每个线程中都有一份各自的值，每次更新的，只是自己线程里的对象的num。
	 */
	public static void error(){
		ThreadTicketError t1 = new ThreadTicketError();
		ThreadTicketError t2 = new ThreadTicketError();
		ThreadTicketError t3 = new ThreadTicketError();
		ThreadTicketError t4 = new ThreadTicketError();
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
}
