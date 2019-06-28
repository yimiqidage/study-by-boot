package com.test.statics;

/**
 * 对于代码语句块的介绍。主要是static语法。
 * 
 *
 */
public class TestStatic {
	
	//代码块的执行顺序：①：静态代码块。②：构造代码块。③：构造函数。
	
	//static 代码语句块，在类初始化时执行
	static {
		System.out.println("static 代码语句块");
	}
	
	//构造对象代码语句块，在对象初始化时执行。创建几个对象，执行几次。
	//调用任意的构造函数进行初始化之前，都会先执行构造代码语句块。
	{
		System.out.println("构造对象代码语句块");
	}
	
	public TestStatic(){
		System.out.println("默认的构造函数");
	}
	
	public TestStatic(String str){
		System.out.println("带参数的构造函数：str："+str);
		
	}
	public static void main(String[] args) {
	    System.out.println("main 语句块");
	    new TestStatic();
	    new TestStatic("哈哈");
    }
	
	
	/**
	 * 简单的静态方法。
	 */
	public static void staticMethod(){
		System.out.println("静态方法：staticMethod");
	}
	
	/**
	 * 证明：非静态方法可以访问静态方法
	 */
	public void notStaticMethod (){
		staticMethod();
	}
}
