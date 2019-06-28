package com.test.system;

import java.util.Properties;
import java.util.Set;

/**
 * @author shiwei 2013-3-25 <br/>
 * 测试System.getProperties()方法：可以用来获得系统的一些参数，如路径分隔符、文件分隔符等。
 *   文件分隔符与路径分隔符的区别：
 * 		①：文件分隔符 [file.separator] UNIX 系统上，此字段的值为 '/'；Windows 系统上，它为 '\\'。 
 * 		②：路径分隔符 [path.separator] 在 UNIX 系统上，此字段为 ':'；Windows 系统上，它为 ';' 。 例如: java   -cp   test.jar;abc.jar   HelloWorld 。另如，在环境变量path中，多个文件的分隔，使用的也是这个。
 * 		上面的是参照File的[字段摘要 ]
 */
public class TestSystem {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		getProperties();
	}

	/**
	 * System的方法介绍：
	 * 		1.Properties集合中，存储的都是String类型的键和值；---> 所以，没有实现泛型。
	 * 		2.Properties实现了map接口。
	 */
	public static void getProperties(){
		
		//test1:getProperties()方法，可以获得系统的所有属性。
		Properties p = System.getProperties();
		
		//test2:可以设置自己的属性.
		System.setProperty("myKey", "myValue");
		
		Set<String> nameSet = p.stringPropertyNames();
		for(String name:nameSet){
			System.out.println(name+"::::"+p.getProperty(name));
		}
		
		//test3:注意：文件分隔符[file.separator]、换行符[line.separator]、路径分隔符[path.separator]
		System.out.println(System.getProperty("file.separator"));
		System.out.println(System.getProperty("line.separator"));
		System.out.println(System.getProperty("path.separator"));
		
		
	}
}
