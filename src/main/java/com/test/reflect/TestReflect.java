package com.test.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author shiwei 2013-4-1 <br/>
 * 反射的使用方法的演示：<br/>
 * 		getClassObj():利用反射，使用空参构造函数，获取字节码对象。<br/>
 * 		getClassObjByConstructor()：使用指定的构造函数，获得字节码对象。<br/>
 * 		getClassMethod()：获得类的指定方法，并传入方法参数，执行；同时也可以获得返回参数。<br/>
 * 		getClassFiled()：获得类的字段，如果想获得私有字段，需要使用 clazz.getDeclaredField("age"); 如果想获得私有字段的值，需要使用field.setAccessible(true)；<br/>
 */
public class TestReflect {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
//		getClassObj();
//		getClassObjByConstructor();
//		getClassMethod();
		getClassFiled();
	}
	
	/**
	 * 利用反射，使用空参构造函数，获取字节码对象。<br/>
	 * 		使用前提：类需要具备无参构造函数，否则，可能抛出IllegalAccessException。<br/>
	 *  clazz.newInstance()：使用空参构造函数实例化。<br/>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static void getClassObj() throws IllegalAccessException ,IllegalAccessException, Exception{
		String className = "com.test.bean.Person";
		//根据类的完整路径，获得Class对象。
		Class clazz = Class.forName(className);
		//使用默认的构造函数[或者空参构造函数]创建对象
		Object obj =  clazz.newInstance();
		System.out.println(obj);
	}
	
	/**
	 * 使用指定的构造函数，获得字节码对象。<br/>
	 * 		class.getConstructor():只能根据共有的构造函数，创建对象。<br/>
	 * 		class.clazz.getConstructor(): 可以根据私有、保护的构造函数，创建对象。<br/>
	 * 
	 * @throws Exception 
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static void getClassObjByConstructor() throws Exception{
		String className = "com.test.bean.Person";
		Class clazz = Class.forName(className);
		//传入构造函数的参数类型。因为构造函数的函数名是固定的，所以不用传函数名。对比方法的执行。
		Constructor con = clazz.getConstructor(String.class,int.class); 
		Object obj = con.newInstance("张三",35);
		System.out.println(obj);
		
	}
	
	/**
	 * 获得类的指定方法，并传入方法参数，执行；同时也可以获得返回参数。<br/>
	 * 		clazz.getMethod()：只能获得共有方法。<br/>
	 * 		clazz.getDeclaredMethod()：可以获得私有方法。<br/>
	 * 		invoke()：执行匹配的方法；同时可以获得方法的返回值。<br/>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static void getClassMethod() throws Exception{
		String className = "com.test.bean.Person";
		Class clazz = Class.forName(className);
		//获得类的实例。
		Object obj = clazz.newInstance();
		//获得指定的方法。
		Method method = clazz.getMethod("reflectTest", String.class,int.class);
		
		//注意：调用method方法，传入的一定要是对象的实例。[使用哪种构造方法获得，无所谓]。并获得返回值。
		String retStr =(String) method.invoke(obj, "李四",88);
		System.out.println(retStr);
		
	}
	
	/**
	 * 获得类的字段：<br/>
	 * 		clazz.getField():只能获得类的共有字段。[访问私有字段，会抛出NoSuchFieldException]<br/>
	 * 		clazz.getDeclaredField():可以获得类的私有字段。<br/>
	 * 		field.setAccessible(true)：如果想获得私有字段的值，需要取消权限检查。设置为ture。[如果不设置，访问私有字段，会抛出IllegalAccessException]<br/>
	 * 		field.getInt(obj)：获得整数结果值。
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static void getClassFiled() throws Exception{
		String className = "com.test.bean.Person";
		Class clazz = Class.forName(className);
		Constructor con = clazz.getConstructor(String.class,int.class);
		Object obj =con.newInstance("赵五",77);
		
		//getDeclaredField():  可以获得私有字段。getField():能获得公共字段。
		Field field = clazz.getDeclaredField("age"); 
		
		//TODO 对于私有成员，可以调用setAccessible(true)方法，取消对私有字段的权限检查。
		field.setAccessible(true);
		
		Integer age = field.getInt(obj);
		System.out.println(age);
		
	}
	
}
