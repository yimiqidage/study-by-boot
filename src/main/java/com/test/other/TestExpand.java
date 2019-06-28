package com.test.other;
import java.util.List;

import com.test.bean.MyUtil;
import static java.util.Collections.*;

/**
 * @author shiwei 2013-3-13 <br/>
 * 思维扩展：<br/>
 * 		①：使用数组实现不定参数的方法，避免一直进行方法的重载。<br/>
 * 				实现便利形式：可变数组。参照  {@link #add_kebian}<br/>
 * 		②：局部代码块：local() ，作用是可以即时释放局部内存，一般不常用。
 */
public class TestExpand {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int sum = add_kebian(1, 2,3,4,5,6);
		System.out.println(sum);
	}

	/**
	 * 使用可变参数实现不定参数个数的相加：<br/>
	 * 可变参数： 格式： ... 三个点号，在内部是通过数组实现的。<br/>
	 * 注：可变参数，一定要定义在参数列表的结尾。
	 * @param arr 实际接收的就是一个数组，只不过接收的是数组的元素，自动将这些元素封装成数组，简化了调用者的书写。
	 * @return
	 */
	public static int add_kebian(int a,int... arr){
		int sum=0;
		for (int i = 0; i < arr.length; i++) {
			sum+=arr[i];
		}
		return sum;
		
	}
	
	/**
	 * 使用数组实现不定参数个数的相加：<br/>
	 * @param arr
	 * @return
	 */
	public static int add_array(int [] arr){
		int sum=0;
		for (int i = 0; i < arr.length; i++) {
			sum+=arr[i];
		}
		return sum;
	}
	
	/**
	 * 测试静态导入：<br/>
	 * 		形式：import static java.util.Collections.*;<br/>
	 * 		将类静态导入以后，可以省略类名，直接调用静态方法。<br/>
	 */
	public static void test_static_import(){
		List<String>list = MyUtil.addSomeString();
		sort(list); // TODO Collections.sort(list); 效果是一样的。
		System.out.println(list);
		
	}
	
	/**
	 * 测试局部代码块:直接使用{}
	 * 对照 if(){} ，格式也是类似的。
	 */
	public static void local(){
		
		{
			int a=3;
			System.out.println(a);
		}
		
	}
	
}
