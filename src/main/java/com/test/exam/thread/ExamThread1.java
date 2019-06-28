package com.test.exam.thread;

/**
 * 
 * @author shiwei 2013-3-22 <br/>
 * 题目一：ExamThread1 是否会报编译错误，在哪一行？
 * 题目二：ThreadTest 可否执行？如果可以执行，输出结果是什么？
 * 
 * 		解答：
 * 			题目一：会报编译错误，因为实现的接口Runnable中，有abstract方法[run()]没有实现。虽然实现了带参数的run方法，但是不是接口的方法。
 * 			题目二：可以执行。执行优先级：子类的run()> 自己实现的Thread(Runnable r) > 父类的run();
 * 					所以执行结果是[subThread run] , 如果删除子类的run方法实现，则打印结果是[runnable run]
 */


/*
public class ExamThread1 implements Runnable{

	public void run (Thread t){
		
	}
}
 */


class ThreadTest{
	public static void main(String[] args) {
		new Thread(new Runnable(){
			public void run(){
				System.out.println("runnable run");
			}
		}){
			public void run(){
				System.out.println("subThread run");
			}
		}.start();
	}
}
