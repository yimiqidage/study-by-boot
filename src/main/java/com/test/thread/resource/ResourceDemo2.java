package com.test.thread.resource;



/*
等待/唤醒机制。 

涉及的方法：

1，wait(): 让线程处于冻结状态，被wait的线程会被存储到线程池中。
2，notify():唤醒线程池中一个线程(任意).
3，notifyAll():唤醒线程池中的所有线程。

这些方法都必须定义在同步中。
因为这些方法是用于操作线程状态的方法。
必须要明确到底操作的是哪个锁上的线程。


为什么操作线程的方法wait notify notifyAll定义在了Object类中？ 

因为这些方法是监视器的方法。监视器其实就是锁。
锁可以是任意的对象，任意的对象调用的方式一定定义在Object类中。

*/
//资源
class Resource2
{
	String name;
	String sex;
	boolean flag = false;
}


//输入
class Input2 implements Runnable
{
	Resource2 r ;
//	Object obj = new Object();
	Input2(Resource2 r)
	{
		this.r = r;
	}
	public void run()
	{
		int x = 0;
		while(true)
		{
			synchronized(r)
			{
				if(r.flag)
					try{r.wait();}catch(InterruptedException e){}
				if(x==0)
				{
					r.name = "mike";
					r.sex = "nan";
				}
				else
				{
					r.name = "丽丽";
					r.sex = "女女女女女女";
				}
				r.flag = true;
				r.notify();
			}
			x = (x+1)%2;

		}
	}
}
//输出
class Output2 implements Runnable
{

	Resource2 r;
//	Object obj = new Object();
	Output2(Resource2 r)
	{
		this.r = r;
	}

	public void run()
	{
		while(true)
		{
			synchronized(r)
			{
				if(!r.flag)
					try{r.wait();}catch(InterruptedException e){}
				System.out.println(r.name+"....."+r.sex);
				r.flag = false;
				r.notify();
			}
		}
	}
}


/**
 * 
 * @author shiwei 2013-3-20 <br/>
 * 视频：[JavaSE基础视频14\24-多线程(线程间通信-等待唤醒机制).avi]
 * 需求：input2 输入资源一次，等待，然后让ouput2输出一次，然后继续输入。。。。循环
 * 
 *
 */
class  Resource2Demo2
{
	public static void main(String[] args) 
	{
		//创建资源。
		Resource2 r = new Resource2();
		//创建任务。
		Input2 in = new Input2(r);
		Output2 out = new Output2(r);
		//创建线程，执行路径。
		Thread t1 = new Thread(in);
		Thread t2 = new Thread(out);
		//开启线程
		t1.start();
		t2.start();
	}
}
