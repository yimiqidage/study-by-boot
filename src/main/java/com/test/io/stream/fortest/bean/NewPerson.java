package com.test.io.stream.fortest.bean;

import java.io.Serializable;

public class NewPerson implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	private String name;
	private int age;
	
	public NewPerson(String name, int age) {
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
