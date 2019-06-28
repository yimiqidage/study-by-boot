package com.test.arrays;

import java.util.Arrays;
import java.util.List;

import com.test.bean.MyUtil;
import com.test.bean.Person;



/**
 * @author shiwei 2013-3-13 <br/>
 *
 * 
 */
public class TestArrays {

	/**
	 * 测试Arrays的一些基本方法。<br/>
	 * Arrays.toString() ： 将数组转化成String ，如果不使用此方法，需要自己手动转换。<br/>
	 * Arrays.asList(arr)：将数组转化成list，但是转化的list中，一些对list长度进行操作的方法不能使用。如 remove<br/>
	 */
	public static void main(String[] args) {

		myToString();
		asList1();
		asList2();
		list_toArray();
		
	}

	public static void myToString() {
		
		int [] arr = {1,2,3,4,5,7,5,4};
		
		System.out.println(Arrays.toString(arr));

	}
	
	/**
	 * Arrays.asList(arr) 测试方法：<br/>
	 * asList1()：数组转换成list以后，可以使用list的一些基本方法。但是改变数组长度的方法不能使用。<br/>
	 * 		如：list.set(index,value) 可以使用，但是 list.remove() 不能使用。<br/>
	 * asList2()：如果数组中的元素是对象[如Integer,String]，则直接将数组中的元素作为集合中的元素进行存储。<br/>
	 * 			     如果数组中的元素是基本类型数据[如 int]，则会将该数组作为集合中的元素进行存储。<br/>
	 * 
	 */
	public static void asList1(){
		String []arr = {"abc","d","b","cc"};
		List<String>list = Arrays.asList(arr);
		
		list.set(0, "ddabc");
		
		//抛出异常：java.lang.UnsupportedOperationException
//		list.add("ddd"); 
		
		System.out.println(list);
	}
	
	/**
	 * 加强测试Arrays.asList
	 */
	public static void asList2(){
		
		//注意区别： demo1：-- 数组中的元素是基本数据类型：int
		int [] arr = {1,2,3,4,5,7,5,4};
		List<int[]> list =  Arrays.asList(arr);
		
		//打印结果是：[[I@de6ced]
		System.out.println(list);  
		
		
		//注意区别： demo2：-- 数组中的元素时对象：Integer
		Integer [] arr2 = {1,2,3,4,5,7,5,4};
		List<Integer> list2 =  Arrays.asList(arr2);
		//打印结果是：[1, 2, 3, 4, 5, 7, 5, 4]
		System.out.println(list2);
		
		
		// 数组中的元素是对象：Person
		Person[] ps ={new Person("zhangsan",11),new Person("lisi",12)};
		List<Person> pslist = Arrays.asList(ps);
		System.out.println(pslist);
		
	}
	
	/**
	 * 其他：测试list的toArray方法<br/>
	 * list.toArray(new String[size]):<br/>
	 * 		如果长度小于list的size，则该方法会创建一个和list的size相同的数组。<br/>
	 * 		如果长度大于list的size，则会使用指定的数组长度，其他默认位置为null。<br/>
	 */
	public static void list_toArray(){
		List<String>list = MyUtil.addSomeString();
		String[] arr = list.toArray(new String[list.size()]);
		System.out.println(Arrays.toString(arr));
	}
}
