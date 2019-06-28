package com.test.son_parent.otherTest;

/**
 * 比TestSonParent2 更复杂一点的。
 * 
 * 父类，子类中都含有静态代码块、构造代码块。观察其执行顺序。
 * 		执行结果：
 * 				Parent...静态代码块..run
 * 				Son...静态代码块..
 *  			Parent...构造代码块..run
 * 			    Parent...构造函数..run
 *  			Son...show...0
 *  			Son...构造代码块..9
 *  			Son...构造函数..10
 * 		总结执行顺序：
 * 				1、父类的静态代码块。
 *  			2、子类的静态代码块。
 *  			3、父类的构造代码块。
 *   			4、父类的构造函数。
 *				5、如果父类的构造函数调用的方法，子类也实现了，则调用子类的实现。
 *				6、子类的构造代码块。
 *				7、子类的构造函数。
 * 
 */
public class TestSonParent3 {

	public static void main(String []args){
		new Son();
	}
}
