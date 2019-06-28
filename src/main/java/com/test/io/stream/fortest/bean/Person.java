package com.test.io.stream.fortest.bean;

import java.io.Serializable;

/**
 * @author shiwei 2013-3-29 <br/>
 * Serializable 为标记接口，不需要实现接口的任何方法。只有实现这个接口，才能标识可以被序列化。 <br/>
 * serialVersionUID：用来判断类和对象是否是同一个版本，数字可以自己随便定义。 <br/>
 * 但是，如果serialVersionUID 相同，但是 类不同，会抛出ClassCastException。 <br/>
 * 注意：对于瞬态[transient 关键字修饰]和静态数据，不会序列化到硬盘上。
 * 		
 * 
 */
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name; // 如果添加transient修饰符，则不会序列化到对象中。private transient String name; 
	private int age; // 如果添加static修饰符，则不会序列化到对象中。
	
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
}
