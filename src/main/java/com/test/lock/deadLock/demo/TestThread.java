package com.test.lock.deadLock.demo;

/**
 * @author shiwei 2013-3-20 <br/>
 * 死锁的实现：两个同步代码块，同时抢夺a、b两个锁
 * 
 */
class TestThread implements Runnable
{
	private boolean flag;
	TestThread(boolean flag)
	{
		this.flag = flag;
	}

	public void run()
	{
		
		if(flag)
		{
//			while(true)
				synchronized(MyLock.locka)
				{
					System.out.println(Thread.currentThread().getName()+"..if   locka....");
					synchronized(MyLock.lockb)				{
						
						System.out.println(Thread.currentThread().getName()+"..if   lockb....");
					}
				}
		}
		else
		{
//			while(true)			
				synchronized(MyLock.lockb)
				{
					System.out.println(Thread.currentThread().getName()+"..else  lockb....");
					synchronized(MyLock.locka)
					{
						System.out.println(Thread.currentThread().getName()+"..else   locka....");
					}
				}
		}

	}

}