package com.test.construction;


/**
 * @author shiwei 2013-3-14 <br/>
 * 测试构造函数中，this的引用。<br/>
 * 	注：如果在一个构造函数中引用另一个构造函数，则this语句必须放到第一行。<br/>
 * 
 */
public class TestConstruction {

	private String name;
	private int age;
	
	public TestConstruction(){
		
	}
	
	public TestConstruction (String name){
		this.name=name;
	}
	
	public TestConstruction (String name,int age){
		this(name); // this 必须放到第一行。 可以测试一下，换位置，就会编译报错。
					//另外，注意引用的形式为：this(name) 
		this.age=age;
		
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
