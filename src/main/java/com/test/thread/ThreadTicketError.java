package com.test.thread;

/**
 * @author shiwei 2013-3-19 <br/>
 *
 * 
 */
public class ThreadTicketError extends Thread {

	private int num = 100;
	
	public void run(){
		while(true){
			if(num>0){
				num--;
				System.out.println("ticket:"+Thread.currentThread().getName()+":"+num);
			}else {
				break;
			}
		}
	}
	
	
}
