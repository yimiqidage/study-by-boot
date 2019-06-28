package com.test.singleton;

/**
 * @author shiwei 2013-3-19 <br/>
 * 测试单例模式：饿汉式、懒汉式<br/>
 * 		饿汉式不会出现线程问题，因为使用的是final,static关键字修饰的，在类加载的时候就被初始化，并且只能赋值一次。<br/>
 * 		懒汉式如果不是用同步代码块会出现线程问题：	<br/>
 * 				过程：线程①先判断s2==null,为真。这个时候执行权被释放了；这时，线程②过来了，也判断s2==null为真，
 * 					如果没有同步代码块，则会被初始化两次，就不是单例模式了。<br/>
 * 					注：在同步代码块里面再判断一次s2==null  是非常必须的，否则还是有可能初始化多个对象。<br/>
 * 
 */
public class TestSingleton {

	/**
	 * 饿汉式单例模式
	 */
	private final static  TestSingleton s = new TestSingleton();
	private TestSingleton(){}
	public static TestSingleton getInstance(){
		return s;
	}
	
	
	/**
	 * 懒汉式单例模式
	 */
	private static TestSingleton s2 = null;
	
	public static TestSingleton getInstance2(){
		
		if(s2==null){//为了提高效率
			synchronized (TestSingleton.class) { // 静态方法中，没有this，所以只能使用 类名.class 作为同步代码快的锁。因为类.class 肯定是只会被初始化一次。
				if(s2==null){
					s2 = new TestSingleton();
				}
			}
		}
		return s2;
	}
	
}
