package com.test.comparator;

import java.util.Comparator;

/**
 * @author shiwei 2013-3-13 <br/>
 * 根据字符串的长度进行排序。
 * 
 */
public class ComparatorByLength  implements Comparator<String>{

	@Override
	public int compare(String o1, String o2) {
		
		int temp = o1.length()-o2.length();
		return temp==0?o1.compareTo(o2):temp;
	}


	
}
