package com.test.map;

import java.util.TreeMap;

import com.test.bean.Animal;
import com.test.set.MyComparator;

/**
 * @author shiwei 2013-3-12 <br/>
 * 如果使用复杂对象，使用TreeMap的排序功能，则需要跟TreeSet一样，传入比较器。
 * 
 */
public class TestTreeMap {
	public static void main(String[] args) {
		complex();
	}

	public static void complex(){
		TreeMap<Animal, String>map = new TreeMap<Animal, String>(new MyComparator());
		map.put(new Animal("tigger",11), "老虎");
		map.put(new Animal("cat",13), "猫");
		map.put(new Animal("dog",15), "狗");
		map.put(new Animal("tigger",11), "大老虎");
		
		System.out.println(map.toString());
	}
}

