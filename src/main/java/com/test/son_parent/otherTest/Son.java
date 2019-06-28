package com.test.son_parent.otherTest;

public class Son extends Parent{

	int num = 9;

	static {
		System.out.println("Son...静态代码块..");
	}
	
	{
		System.out.println("Son...构造代码块.."+num);
		num = 10;
	}
	public Son(){
		//显示初始化，执行num=9.
		//构造代码块初始化：执行构造代码块。
		System.out.println("Son...构造函数.."+num);
		
	}
	public void show(){
		System.out.println("Son...show..."+num);
	}
}
