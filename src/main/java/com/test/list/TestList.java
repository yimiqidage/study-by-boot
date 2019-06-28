package com.test.list;

import java.util.ArrayList;
import java.util.List;

import com.test.bean.Person;

/**
 * 使用list测试泛型<br/> 
 * ①：泛型的擦除：泛型在编译以后的class中，是不存在的。如List<String>list = new ArrayList<String>(); 编译完以后是：List list = new ArrayList(); <br/>
 * ②：泛型的补偿：在执行的时候，会自动调用 object.getClass().getName() 获得对象的类型。<br/>
 * 
 */
public class TestList {

	public static void main(String[] args) {
		test();
		testStringList();
		
	}
	

	public static void testStringList() {
		List<String>list = new ArrayList<String>();
		list.add("str1");
		list.add("str2");
		list.add("str3");
		for(String str:list){
			System.out.println(str.getClass().getName());
		}
	}
	
	public static void test (){
		List<Person>persons = new ArrayList<Person>();
		persons.add(new Person("张三",10));
		persons.add(new Person("李四",11));
		persons.add(new Person("王五",12));
		
		for(Person person:persons){
			System.out.println(person.getClass().getName());
		}
	}
}
