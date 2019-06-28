package com.test.thread.join;

public class TestJoinThread implements Runnable {

	public void run(){
		for(int i=0;i<50;i++){
			System.out.println(Thread.currentThread().toString()+",method:run");
		}
	}
	
}

/**
 * 
 * @author shiwei 2013-3-22 <br/>
 * join(): 等待该线程终止。 换句话理解就是：t1.join() 方法调用以后，主线程main会释放执行权，只有当t1执行完毕以后，主线程main才会继续执行。
 *
 */
class Demo2 {
	public static void main(String[] args) throws Exception{
		TestJoinThread t = new TestJoinThread();
		Thread t1 = new Thread(t);
		Thread t2 = new Thread(t);
		t1.start();
		t2.start();
		t1.join(); //调用join方法以后，主线程main会释放执行权，然后让t1先执行；但是t2也有执行资格，所以t1，t2会抢夺执行权。
				   //只有当t1执行完毕以后，主线程main才会继续执行。不管t2是否执行完毕，都会继续执行。
		
		for(int i=0;i<50;i++){
			System.out.println(Thread.currentThread().toString()+",method:main");
		}
		
	}
}

