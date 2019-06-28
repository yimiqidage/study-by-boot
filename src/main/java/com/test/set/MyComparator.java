package com.test.set;

import java.util.Comparator;

import com.test.bean.Animal;

/**
 * @author shiwei 2013-3-11 <br/>
 * 自定义实现Comparator的比较器：根据name进行排序
 * 
 */
public class MyComparator  implements Comparator<Animal> {

	@Override
	public int compare(Animal o1, Animal o2) {
		
		int temp = o1.getName().compareTo(o2.getName());
		
		return temp==0?o1.getAge()-o2.getAge():temp;
	}

}
