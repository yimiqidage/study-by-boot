package com.test.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @author shiwei 2013-3-8 <br/>
 * 演示ListIterator的使用环境
 * 
 */
public class TestListIterator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

			testListIterator();
			testterator();
	}

	/**
	 * 在进行迭代的时候，使用listIterator操作集合，可以正常操作。
	 */
	public static void testListIterator() {
		
		List<String>list = new ArrayList<String>();
		list.add("abc1");
		list.add("abc2");
		list.add("abc3");
		
		ListIterator<String> iterator = list.listIterator();
		while(iterator.hasNext()){
			String curr = iterator.next();
			if(curr.equals("abc2")){
				iterator.add("abc222222");
			}
		}
		
		System.out.println(list.toString());
	}

	/**
	 * 在进行迭代的时候，使用iterator操作集合。抛出异常：java.util.ConcurrentModificationException
	 */
	public static void testterator() {
		
		List<String>list = new ArrayList<String>();
		list.add("abc21");
		list.add("abc22");
		list.add("abc23");
		
		Iterator<String> iterator = list.iterator();
		while(iterator.hasNext()){
			String curr = iterator.next();
			if(curr.equals("abc22")){
				list.add("abc222222");
			}
		}
		
		System.out.println(list.toString());
	}
	
}
