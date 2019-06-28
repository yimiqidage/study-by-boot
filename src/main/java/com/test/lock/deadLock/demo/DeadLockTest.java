package com.test.lock.deadLock.demo;


/**
 * @author shiwei 2013-3-20 <br/>
 * 创建死锁示例：
 * 		MyLock 只有两个对象，分别作为两个锁。
 * 		TestThread 线程中，只有MyLock的两个锁的资源都拿到时，才会执行。造成互相抢锁的情况。
 * 
 */
class DeadLockTest 
{
	public static void main(String[] args) 
	{
		TestThread a = new TestThread(true);
		TestThread b = new TestThread(false);

		Thread t1 = new Thread(a);
		Thread t2 = new Thread(b);
		t1.start();
		t2.start();
	}
}
