package com.test.thread;


/**
 * @author shiwei 2013-3-15 <br/>
 * 实现方法一：继承Thread类
 * 
 */
public class ThreadDemo extends Thread{
	
	private String name;
	
	public ThreadDemo(String name){
		super(name);
		this.name = name;
		
	}
	
	public void run(){
		for(int i=0;i<10;i++){
			System.out.println(this.name+"........."+i);	
		}
		
	}
	
}
