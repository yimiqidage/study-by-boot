package com.test.set;

import java.util.Comparator;
import java.util.TreeSet;

import com.test.bean.Animal;
import com.test.bean.Person;


/**
 * @author shiwei 2013-3-11 <br/>
 * 普通对象
 * 		simple():如String，自己实现了comparable接口，可以直接进行比较。
 * 对于集合对象
 * 		complex_compare1():对象自身实现Comparable接口，并实现接口的compareTo方法。如TreeSet<Person>ts = new TreeSet<Person>();
 * 		complex_compare2():通过在初始化时，传入比较器对象，进行比较。TreeSet<Animal>ts = new TreeSet<Animal>(new MyComparator()); 注意：传入的比较器需要实现Comparator 接口.不是comparable
 * 		complex_fail():定义的对象和初始化时,都没有实现比较方法,则抛出异常.
 * 注意:实现了comparable的,都是对象自身和另一个对象进行比较,所以传入的参数只有一个.而Comparator接口,是需要传入两个对象,比较这两个对象的.
 * 使用扩展：如果想使用TreeSet实现ArrayList的类似功能，即显示的顺序和插入的顺序一致，可以参考complex_extend()
 */
public class TestTreeSet {

	/**
	 * TreeSet：可以对set中的集合进行排序，是不同步的。
	 */
	public static void main(String[] args) {
		
		simple();
		complex_compare1();
		complex_compare2();
//		complex_fail();
		new TestTreeSet().complex_extend();
	}
	
	/**
	 * java自定义的一些基本类型，已经实现了Comparable接口，可以直接使用。
	 */
	public static void simple(){
		TreeSet<String>ts = new TreeSet<String>();
		ts.add("a");
		ts.add("d");
		ts.add("b");
		ts.add("c");
		System.out.println("simple:"+ts.toString());
	}
	

	/**
	 * 自定义的对象，自己实现Comparable接口，会正常比较。
	 */
	public static void complex_compare1(){
		TreeSet<Person>ts = new TreeSet<Person>();
		ts.add(new Person("zhang2",22));
		ts.add(new Person("zhang3",28));
		ts.add(new Person("zhang1",21));
		
		System.out.println("complex_compare1:"+ts);
	}
	
	
	/**
	 * 自定义的对象，没有实现Comparable接口，但是在初始化的时候，传入了一个实现了Comparator接口的类对象，就会使用传入的比较器对象。
	 */
	public static void complex_compare2(){
		TreeSet<Animal>ts = new TreeSet<Animal>(new MyComparator());
		ts.add(new Animal("zhang2",22));
		ts.add(new Animal("zhang3",23));
		ts.add(new Animal("zhang1",21));
		
		System.out.println("complex_compare2:"+ts);
	}
	
	/**
	 * 自定义的对象，没有实现Comparable接口，会抛出异常：java.lang.ClassCastException
	 */
	public static void complex_fail(){
		TreeSet<Animal>ts = new TreeSet<Animal>();
		ts.add(new Animal("zhang2",22));
		ts.add(new Animal("zhang3",23));
		ts.add(new Animal("zhang1",21));
		
		System.out.println(ts);
	}
	
	/**
	 * 扩展测试：使用TreeSet实现输出和输入顺序一致。
	 */
	public void complex_extend(){
		TreeSet<String> set = new TreeSet<String>(new Comparator1());
		set.add("a");
		set.add("b");
		set.add("d");
		set.add("c");
		System.out.println("complex_extend:"+set);
	}
	
	public class Comparator1 implements Comparator<String>{
		@Override
		public int compare(String o1, String o2) {
			return 1;
		}
	}

}
