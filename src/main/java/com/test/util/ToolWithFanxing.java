package com.test.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.test.bean.Person;
import com.test.bean.Student;
import com.test.bean.Teacher;

/**
 * @author shiwei 2013-3-7 <br/>
 * 测试泛型类，泛型方法，泛型参数，泛型接口，泛型接口的实现。<br/>
 * 用来测试泛型，注意：其中的字母E可以换成别的任意字符。如下面的注释以后的代码，效果跟现在这个是一样的。<br/>
 * 
 * public static <T> T getFirstCollection(Collection<T> col) : 概述：
 * 			方法中的参数col的类型<T>因为跟类定义的类型不一致，所以需要声明。在方法中声明的方式就是在返回值的前面添加<T> ，然后就可以使用了。
 * 
 */
public class ToolWithFanxing <E>{
	
	private E e;

	public E getValue() {
		return e;
	}

	public void setValue(E value) {
		this.e = value;
	}
	
	/**
	 * 传入的参数类型必须和指定的类型一致，也就是初始化对象时，传入的类型一致。
	 * @param e
	 */
	public void show(E e){
		System.out.println("show:"+e);
	}
	
	/**
	 * 可以是传入的任何类型的对象，字母W可以换成任意字母
	 * show方法和showEvery方法的区别在于：
	 * 			show方法使用的类型，在定义ToolWithFanxing对象时，就已经确定了。必须跟对象的类型一致。
	 * 			showEvery方法的类型，则是任意的。
	 * @param <W>
	 * @param str
	 */
	public <W> void showEvery(W str){
		System.out.println("showEvery:"+str);
	}
	
	/**
	 * 只能是Object对象
	 * @param o
	 */
	public void showObject(Object o){
		System.out.println("showObject:"+o.toString());
	}
	
	/**
	 * 静态方法中，正确使用泛型定义demo：
	 * 对于静态方法，不能直接访问类中定义的类型，例如 staitcShowFalse 方法，会编译错误。只能自定义类型， 如 staticShowTrue <br/>
	 * 自定义类型一定要放到返回值类型的前面，修饰符的后面。 如 static <E> void  中的 <E>
	 * @param <E>
	 * @param e
	 */
	public static <E> void staticShowTrue(E e){
		System.out.println("staticShow:"+e);
	}
	
	/**
	 * 静态方法中，错误的使用泛型定义demo
	 * @param args
	 */
//	public static void staitcShowFalse(E e){
//		System.out.println("staitcShowFalse:"+e);
//	}
	
	public static void main(String args[]){
		//①：泛型使用定义
		ToolWithFanxing<String> tool = new ToolWithFanxing<String>();
		//②：测试泛型方法
		//tool定义时，传入的类型为String,所以只可以打印String类型的对象。
		tool.show("string");
		
		// TODO 注意添加时，需要使用相同的类型。
		ToolWithFanxing<Integer>ints = new ToolWithFanxing<Integer>();
		ints.show(new Integer(4)); 
		
		
		tool.showEvery("string"); //showEvery 因为是根据传入的方法的参数自动转换，可以使用任意类型
		
		//TODO 注意showEvery和show方法的区别：
		//	   show方法对应的参数，在对象声明时，就已经定义好了。showEvery方法对应的参数，可以是任意类型，跟ToolWithFanxing对象类型没关系。
		tool.showEvery(new Integer(4)); //可以使用Integer
		
		tool.showObject(new Object());//showObject 就是一般的方法了，只能传入Object对象
		
		System.out.println("-------------  测试通配符 ? -------------");
		//③：测试通配符 ?
		List<String>strList = new ArrayList<String>();
		strList.add("abc:list");
		strList.add("def:list");
		
		List<Integer>intList = new ArrayList<Integer>();
		intList.add(3);
		intList.add(4);
		
		Set<String>set = new HashSet<String>();
		set.add("abc:set");
		set.add("def:set");
		
		printCollection(strList);
		printCollection(intList);
		printCollection(set);
		
		//④：测试泛型：  ? extends Person 只要是person的子类或者Person，都可以。
		List<Student>stus = new ArrayList<Student>();
		stus.add(new Student("a",11));
		stus.add(new Student("b",12));
		
		List<Teacher>teas = new ArrayList<Teacher>();
		teas.add(new Teacher("c",21));
		teas.add(new Teacher("d",22));
		
		
		List<Person>pers = new ArrayList<Person>();
		pers.add(new Person("e",31));
		pers.add(new Person("f",32));  
		pers.add(new Teacher("g",33));// TODO 注意：这里可以添加 Teacher对象。
		
		printCollection3(stus);
		printCollection3(teas);
		printCollection3(pers);
		
		//TODO 注意：除了person以及它的子类意外，其他类型不能调用这个方法
//		printCollection3(strList);
		
		//④：测试泛型：  ? super Student 只要是Student的父类或者Student，都可以。
		printCollection4(stus);
		//TODO 注意： pers中添加了一个Teacher对象，它既不是Student的父类，也不是Student，但是，可以添加进去，并且可以运行。
		printCollection4(pers);
	}
	

	/**
	 * 定义泛型接口
	 *
	 */
	public interface Inter<E> {
		public void show(E e);
	}
	
	/**
	 * 实现泛型接口方式一：在实现的时候，指定类型。如，这个实现为String
	 *
	 */
	public class InterImpl implements Inter<String>{
		public void show(String str){
			System.out.println(str);
		}
	}
	
	/**
	 * 实现泛型接口方式二：在实现的时候，不指定类型。
	 *
	 */
	public class InterImpl2 <W> implements Inter<W>{
		public void show(W e){
			System.out.println(e);
		}
	}
	
	/**
	 * 泛型的通配符 1 [?] <br/>
	 * 有两个注意点：<br/>
	 * ①：使用List,Arraylist,HashSet 的父类Collection 为参数，可以兼容更多的类型。<br/>
	 * ②：参数使用不确定的类型的泛型[也就是使用问号]，可以在调用方法的时候，指定类型，兼容更多的调用者。<br/>
	 * 这一个方法，可以被不同的List<String>strList,List<Integer>intList,Set<String>set 多种类型使用。<br/>
	 * @param col
	 */
	public static void printCollection(Collection<?> col){
		Iterator< ? > it = col.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
	
	/**
	 * 泛型的通配符 2 [T]<br/>
	 * 这个方法同 {@link #printCollection}  方实现效果是一致的 。 <br/>
	 * 这个方法中的参数，使用新的类型<T>，跟类定义的<E> 不是一个类型，所以，需要在方法中进行声明，声明的方式就是在返回值前面添加<T>.
	 * @param <T>
	 * @param col
	 * @return
	 */
	public static <T> void printCollection2(Collection<T> col){
		Iterator< T > it = col.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
	/**
	 * 泛型的通配符 3 [T]<br/>
	 * 返回泛型的对象：可以根据调用者的类型，返回对应的对象类型。<br/>
	 * 如：如果是List<String>调用，则返回 String, 如果是 List<Integer> 调用，则返回 Integer
	 * @param <T>
	 * @param col
	 * @return
	 */
	public static <T> T getFirstCollection(Collection<T> col){
		Iterator< T > it = col.iterator();
		return it.next();
	}
	
	/**
	 * 概括讲： <br/>
	 * ①：? extends E :接收E类型或者E的子类型的对象，这是上限。 <br/>
	 * ②：? super E: 接受E类型或者E的父类的对象，这是下限。 <br/>
	 *  
	 * 泛型的通配符 4 [? extends Person] <br/>
	 * 只允许person和person的子类可以调用的方法。
	 * @param col
	 */
	public static void printCollection3 (Collection<? extends Person> col){
		Iterator< ? extends Person> it = col.iterator();
		while(it.hasNext()){
			Person p = it.next();
			System.out.println("[? extends Person]: "+p.getName()+","+p.getAge());
		}
	}
	/**
	 * 泛型的通配符 5 [? super Student] <br/>
	 * 只允许Student和Student的父类可以调用的方法。
	 * @param col
	 */
	public static void printCollection4 (Collection<? super Student> col){
		Iterator< ? super Student> it = col.iterator();
		while(it.hasNext()){
			System.out.println("[? super Student] :"+it.next().toString());
		}
	}
	

	/**
	 * 泛型中，点号的使用方式： int ... arr
	 * @param arr
	 */
	public static void other(int ... arr){
		for(int i=0;i<arr.length;i++){
			System.out.println(arr[i]);
		}
	}
	
}





//public class ToolUtil <QQ>{
//	private QQ q;
//
//	public QQ getQ() {
//		return q;
//	}
//
//	public void setQ(QQ q) {
//		this.q = q;
//	}
//	
//}

