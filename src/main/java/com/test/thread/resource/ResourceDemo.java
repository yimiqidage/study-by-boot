package com.test.thread.resource;

/*
线程间通讯：
多个线程在处理同一资源，但是任务却不同。
测试资源：ResourceDemo.java   -----> 最后面
*/

//资源
 class Resource
{
	String name;
	String sex;
}

//输入
class Input implements Runnable
{
	Resource r ; 
//	Object obj = new Object();
	Input(Resource r)
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
			}
			x = (x+1)%2;

		}
	}
}
//输出
class Output implements Runnable
{

	Resource r;
//	Object obj = new Object();
	Output(Resource r)
	{
		this.r = r;
	}

	public void run()
	{
		while(true)
		{
			synchronized(r)
			{
				System.out.println(r.name+"....."+r.sex);
			}
		}
	}
}


/**
 * 
 * @author shiwei 2013-3-20 <br/>
 * 视频：[JavaSE基础视频14\23-多线程(线程间通信-示例).avi]
 * 需求：测试两个线程同时操作同一个资源：其中一个线程往资源中添加值，另一个线程取资源中的值。并保证不会发生错误。
 * 测试步骤：
 * 		①：不添加同步代码块，直接执行。-----> 结果：会出现串数据，如：mike,女女女女女女
 * 		②：添加同步代码块，但使用两个对象，如：分别在各自的run方法中Object obj = new Object(); ---> 结果还是一样。
 * 		③：添加同步代码块，但是使用同一个资源：r；也就是现在的结果。---> 不会出现串行。
 * 
 * 分析：产生串行的原因：多个线程在抢夺同一个资源[Resources r],当Input抢到执行权的时候，刚给name赋值为mike，这时，执行权被Ouput抢到，执行输出。这时就会出现mike,女女女女女 的结果了。
 * 		解决办法：必须针对同一个资源添加同步代码块。也就是传入的r。
 *
 */
class  ResourceDemo
{
	public static void main(String[] args) 
	{
		//创建资源。
		Resource r = new Resource();
		//创建任务。
		Input in = new Input(r);
		Output out = new Output(r);
		//创建线程，执行路径。
		Thread t1 = new Thread(in);
		Thread t2 = new Thread(out);
		//开启线程
		t1.start();
		t2.start();
	}
	
}
