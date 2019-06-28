/**
 * 
 */
package com.test.io.stream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.test.io.stream.fortest.bean.Person;


/**
 * @author shiwei 2013-3-29 <br/>
 * 
 * 序列流：
 * 		ObjectInputStream : writeObj()
 * 		ObjectOutputStream : readObj()
 * 
 * 注意：一般序列化对象的扩展名为 .object；被操作的对象需要实现Serializable 接口
 * 
 * 视频地址：JavaSE基础视频24\IO流\55 - 58
 * 
 * 
 */
public class TestObjectStream {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		readObj();
	}
	
	
	/**
	 * 演示存储序列化对象。
	 * 序列化的对象需要实现Serializable 接口
	 * @throws IOException
	 */
	public static void writeObj() throws IOException{
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\test\\person.object"));
		
		oos.writeObject(new Person("张三",20));
		
		oos.close();
		
	}

	
	/**
	 * 演示读取序列化对象。[对象的反序列化]
	 * 抛出ClassNotFoundException异常是因为：
	 * 		对象在堆内存中创建都需要依赖该对象所属的类文件，即.clsss文件，如果.class找不到，就会抛出ClassNotFoundException
	 * 
	 * 测试：
	 * 		①：使用Person进行序列化，使用NewPerson进行反序列化。				----> 结果： 抛出ClassCastException
	 * 		②：定义Person的serialVersionUID值为1L，修改其中的属性，然后调用。  ----> 结果： 可以正常读取。
	 * 						原因：serialVersionUID一致，就会认为是一个版本。
	 * 		③：将age属性改为静态变量。										---->结果：读取后，数据丢失。	
	 * 						原因：ObjectOutputStream 不会对静态和瞬态数据进行存储，
	 * 注意：对于瞬态和静态数据，不会序列化到硬盘上。
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void readObj() throws IOException, ClassNotFoundException {
		
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:\\test\\person.object"));
		
		Person p = (Person)ois.readObject();
		
		System.out.println(p.getName()+":"+p.getAge());
		
		ois.close();
		
		
	}
}
