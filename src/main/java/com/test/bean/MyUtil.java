package com.test.bean;

import java.util.ArrayList;
import java.util.List;

public class MyUtil {

	
	/**
	 * 添加一些Animal对象
	 * @return
	 */
	public static List<Animal> addSomeAnimal (){
		List<Animal> as = new ArrayList<Animal>();
		as.add(new Animal("tigger",11));
		as.add(new Animal("cat",13));
		as.add(new Animal("dog",15));
		
		return as;
	}
	
	public static List<String>addSomeString(){
		List<String>strs = new ArrayList<String>();
		strs.add("bc");
		strs.add("ab");
		strs.add("def");
		strs.add("aa");
		strs.add("zbcdn");
		strs.add("cdefgd");
		return strs;
	}
}

