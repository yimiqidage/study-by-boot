package com.test.innerclass;

/**
 * @author shiwei 2013-4-2 <br/>
 * 
 * 匿名内部类：格式：new 父类or接口(){子类内容}
 * 		1、就是内部类的简写格式。
 * 		2、内部类必须继承或者实现一个外部类或者接口。
 * 
 */
abstract class Demo
{
	abstract void show();
}

class Outer2
{
	int num = 4;
	/*
	class Inner extends Demo
	{
		void show()
		{
			System.out.println("show ..."+num);
		}
	}
	*/
	public void method()
	{
		//new Inner().show();
		new Demo()//匿名内部类。
		{
			void show()
			{
				System.out.println("show ........"+num);
			}
		}.show();
	}
}


class InnerClassDemo4 
{
	public static void main(String[] args) 
	{
		new Outer2().method();
	}
}
