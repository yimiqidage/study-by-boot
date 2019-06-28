package com.test.list;

import java.util.LinkedList;

/**
 * @author shiwei 2013-3-8 <br/>
 * 使用linkedList实现队列；如果想实现堆栈，只需要修改myAdd方法调用link.addFirst() 即可。
 * 
 */
public class TestMyLinkedList<E> {
	
	private LinkedList<E> link;
	
	public TestMyLinkedList(){
		link = new LinkedList<E>();
	}

	// 实现的是先进先出，也就是队列
	
	public void myAdd(E e){
		link.addLast(e);
	}
	
	public E myGet(){
		return link.removeFirst();//这个方法获取第一个元素，并删除。
	}
	
	public boolean isEmpty(){
		return link.isEmpty();
	}
	
	/**
	 * 引用示例，实现队列的先进先出。
	 * @param args
	 */
	public static void main (String [] args){
		TestMyLinkedList<String> links = new TestMyLinkedList<String>();
		links.myAdd("abc1");
		links.myAdd("abc2");
		links.myAdd("abc3");
		
		while(!links.isEmpty()){
			System.out.println(links.myGet());
		}
	}
}
