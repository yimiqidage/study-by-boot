package com.test.son_parent;

public class TestSonParent2 {

	/**
	 * 显示结果：
	 *	 Fu2...constructor..run
	 *	 Zi2...show...0
	 *	 constructor...code..9
	 *	 Zi2...constructor..10
	 *
	 * 步骤：新建一个子类对象。
	 *		1、执行子类构造函数。
	 *		2、子类调用父类构造函数:super()
	 *		3、子类实现了父类的show方法，所以执行子类的show。
	 *		4、显示初始化数据，即执行 num= 9 。
	 *		5、执行子类的构造代码块。
	 *		6、构造函数具体初始化，执行:System.out.println("Zi2...constructor.."+num);
	 *		
	 */
	public static void main(String[] args) {
		new Zi2();
	}

	
	
}

class Fu2{
	
	public Fu2(){
		System.out.println("Fu2...constructor..run");
		show();
	}
	
	public void show(){
		System.out.println("Fu2...show");
	}
}


class Zi2 extends Fu2{
	int num = 9;
	{
		System.out.println("constructor...code.."+num);
		num = 10;
	}
	public Zi2(){
		//显示初始化，执行num=9.
		//构造代码块初始化：执行构造代码块。
		System.out.println("Zi2...constructor.."+num);
		
	}
	public void show(){
		System.out.println("Zi2...show..."+num);
	}
}