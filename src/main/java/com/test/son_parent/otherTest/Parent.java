package com.test.son_parent.otherTest;

public class Parent {

	static{
		System.out.println("Parent...静态代码块..run");
	}
	
	{
		System.out.println("Parent...构造代码块..run");
	}
	public Parent(){
		System.out.println("Parent...构造函数..run");
		show();
	}
	
	public void show(){
		System.out.println("Parent...show");
	}
	
}
