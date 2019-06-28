package com.test.collections;

import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import com.test.bean.Animal;
import com.test.bean.MyUtil;
import com.test.comparator.ComparatorByLength;
import com.test.set.MyComparator;

/**
 * @author shiwei 2013-3-12 <br/>
 * 测试Collections的方法。
 * 	Collections.sort() 对集合排序。
 * 	Collections.reverseOrder()，可以对指定集合进行反向排序。
 *  Collections.binarySearch()，查找集合所在的位置。
 *  Collections.fill(x)，将集合中的元素都替换成x。
 *  Collections.shuffle()，将集合中的元素打乱，进行随机替换。
 * 
 */
public class TestCollections {

	public static void main(String[] args) {
		sort();
		reverseOrder();
		reverseOrder2();
		binarySearch();
		enumeration();
	}
	
	/**
	 * 测试Collections的sort方法。传入一个集合，和指定的比较器即可。
	 */
	public static void sort() {
		
		List<Animal>as = MyUtil.addSomeAnimal();
		Collections.sort(as, new MyComparator());
		System.out.println(as);
		
		List<String>strs = MyUtil.addSomeString();
		Collections.sort(strs);
		System.out.println(strs);
	}
	
	/**
	 * 使用Collections.reverseOrder() 作为参数，传入sort方法，可以对自然排序进行逆序排序。
	 */
	public static void reverseOrder(){
		List<String>strs = MyUtil.addSomeString();
		Collections.sort(strs,Collections.reverseOrder());
		System.out.println("reverseOrder---------"+strs);
	}
	
	/**
	 * Collections.reverseOrder(Comparator<String> cmp)) 中，传入指定的比较器，进行逆向排序。
	 */
	public static void reverseOrder2(){
		List<String>strs = MyUtil.addSomeString();
		Collections.sort(strs,Collections.reverseOrder(new ComparatorByLength()));
		System.out.println("reverseOrder2----------"+strs);
	}
	
	
	/**
	 * binarySearch：返回查找元素的角标。如果找不到，则返回应该插入的元素角标-1。
	 */
	public static void binarySearch(){
		List<String>strs = MyUtil.addSomeString();
		Collections.sort(strs);
		System.out.println(strs);
		int index = Collections.binarySearch(strs, "ab");
		System.out.println("index:"+index);
		
	}
	
	public static void enumeration(){
		List<String>strs = MyUtil.addSomeString();
		Enumeration<String> enmus = Collections.enumeration(strs);
		while(enmus.hasMoreElements()){
			System.out.print(enmus.nextElement()+",");
		}
		
	}
	
	

	
}
