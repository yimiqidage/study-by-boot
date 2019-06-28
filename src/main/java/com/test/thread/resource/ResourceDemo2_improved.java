package com.test.thread.resource;
/**
 * 
 * @author shiwei 2013-3-21 <br/>
 * Resource2 的改进版，将设置值，输出的同步语句写到了资源里面，创建了同步方法。这样，在外面直接调用就可以了。
 *
 */

class Resource2_improved{
	private String name;
	private String sex;
	private boolean flag = false;
	public synchronized void setParam(String name,String sex){
		
		if(this.flag){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		this.name = name;
		this.sex=sex;
		this.flag=true;
		
		notify();
		
	}
	
	public synchronized void out(){
		
		if(!this.flag){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(this.name+"......."+this.sex);	
		this.flag=false;
		
		notify();
	}
	
	
}

class Input2_improved implements Runnable{
	Resource2_improved r;
	Input2_improved (Resource2_improved r){
		this.r=r;
	}
	
	public void run(){
		
		int x = 0;
		while(true){
			
				if(x%2==0){
					r.setParam("mike", "nan");
				}else{
					r.setParam("丽丽", "女女女女女");
				}
			
			x++;
		}
	}
}


class Ouput2_improved implements Runnable{
	Resource2_improved r ;
	
	Ouput2_improved (Resource2_improved r){
		this.r=r;
	}
	
	public void run (){
		while(true){
			r.out();
		}
	}
}
/**
 * @author shiwei 2013-3-21 <br/>
 *
 * 
 */
public class ResourceDemo2_improved {

	public static void main(String[] args) {
		Resource2_improved r = new Resource2_improved();
		Input2_improved in  = new Input2_improved(r);
		Ouput2_improved out = new Ouput2_improved(r);
		Thread t1 = new Thread(in);
		Thread t2 = new Thread(out);
		t1.start();
		t2.start();
	}
	
}
