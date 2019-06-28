package com.test.set;

import java.util.HashSet;
import java.util.LinkedHashSet;


/**
 * @author shiwei 2013-3-11 <br/>
 * LinkedHashSet的使用demo<br/>
 * linkedHashSet,会保证显示顺序 和添加的顺序一致.
 * 
 */
public class TestLinkedHashSet {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		simple();
		
	}

	/**
	 * 简单示例：添加基本类型<br/>
	 * linkedHashSet,会保证显示顺序和添加的顺序一致.
	 */
	private static void simple() {
		HashSet<String>set = new LinkedHashSet<String>();
		set.add("abc1");
		set.add("abc2");
		set.add("abc3");
		set.add("abc4");
		set.add("abc3");
		System.out.println(set.toString());
	}

	
}

