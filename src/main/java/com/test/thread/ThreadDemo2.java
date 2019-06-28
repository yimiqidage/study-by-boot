package com.test.thread;

/**
 * @author shiwei 2013-3-18 <br/>
 * 实现方式二：实现Runnable接口
 * 
 */
public class ThreadDemo2 implements Runnable {

	@Override
	public void run() {
		for(int i=0;i<10;i++){
			System.out.println("runnable:"+i);	
		}
	}

	
}
