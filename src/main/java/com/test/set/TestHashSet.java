package com.test.set;

import java.util.HashSet;

import com.test.bean.Animal;
import com.test.bean.Person;


public class TestHashSet {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		simple();
		complex();
		complex_new();
	}

	/**
	 * 简单示例：添加基本类型
	 */
	private static void simple() {
		HashSet<String>set = new HashSet<String>();
		set.add("abc1");
		set.add("abc2");
		set.add("abc3");
		set.add("abc4");
		set.add("abc3");
		System.out.println(set.toString());
	}

	/**
	 * 添加复杂对象，如Animal。即使name和age相同，也会重复添加
	 */
	public static void complex (){
		HashSet<Animal>set = new HashSet<Animal>();
		set.add(new Animal("tiger",21));
		set.add(new Animal("cat",22));
		set.add(new Animal("dog",23));
	
		set.add(new Animal("tiger",21));
		
		System.out.println(set.toString());
	}
	
	/**
	 * 对复杂对象进行覆写hashcode和equals方法，相同的name和age认为是同一个对象
	 */
	public static void complex_new(){
		HashSet<Person>ps = new HashSet<Person>();
		ps.add(new Person("zhang1",21));
		ps.add(new Person("zhang2",22));
		ps.add(new Person("zhang3",23));
		ps.add(new Person("zhang1",21));
		
		System.out.println(ps.toString());
	}
}

