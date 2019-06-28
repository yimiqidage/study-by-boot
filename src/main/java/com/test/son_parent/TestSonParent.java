package com.test.son_parent;

/**
 * @author shiwei 2013-4-2 <br/>
 * 测试子类、父类的执行。
 * 		在执行子类的构造方法时，会先执行父类的构造方法。
 * 		在构造函数中，如果引用父类的构造函数，即 super() 代码，必须将此代码放到第一行，否则会编译报错。
 */
public class TestSonParent {

	/**
	 * 执行结果为：
	 * 		Zi...show:0
	 * 		Zi....constructor:9
	 * 
	 * 原因：
	 * 		1、在子类Zi()的构造方法中，会有默认的super()，也就是先执行父类的构造方法。
	 * 		2、父类的构造方法中，调用show()方法。
	 * 		3、因为子类实现了show()方法，所以调用子类的show()
	 * 		4、子类show()方法调用了变量num，但是这个时候还没有初始化，所以显示的num为0.
	 * 		5、super() 执行完毕，开始执行子类的构造方法。
	 * 		6、初始化num=9。
	 * 		7、执行构造方法，显示num
	 * 
	 */
	public static void main(String[] args) {
		new Zi();
	}

}


class Fu{
	public Fu (){
		show();
	}
	
	public void show(){
		System.out.println("Fu....constructor");
	}
}

class Zi extends Fu{
	int num = 9;
	public Zi(){
		//下面这行代码，写和不写，效果一样
//		super();
		System.out.println("Zi....constructor:"+num);
	}
	
	public void show(){
		System.out.println("Zi...show:"+num);
	}
}