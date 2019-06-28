package com.test.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * @author shiwei 2013-3-28 <br/>
 * Properties集合：
 * 特点：
 * 1，该集合中的键和值都是字符串类型。
 * 2，集合中的数据可以保存到流中，或者从流获取。
 * 通常该集合用于操作以键值对形式存在的配置文件。 
 * 
 * 演示Properties的一些基本用法。
 * 		testList(): 演示Properties的list(PrintStream p) 方法.
 * 
 */
public class TestProperties {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		test_save_update();
	}
	
	/**
	 * 测试Properties的list(PrintStream p) 方法：
	 * 		该方法主要用于调试，PrintStream 属于字节流。
	 */
	public static void testList(){
		
		Properties pro  = System.getProperties();
		pro.list(System.out);
	}
	
	/**
	 * 存储：直接将Properties转换成键值对，存储到文件当中。使用store 方法。注意：注释信息不要填写中文，否则会以unicode码存储。
	 * @throws IOException
	 */
	public static void testStore() throws IOException{
		Properties pro  = createDate();
		FileOutputStream fos = new FileOutputStream("D:\\test\\demo.txt");
		pro.store(fos, "name+age：created by "+TestProperties.class);
		fos.close();
	}
	
	/**
	 * 测试load方法，参数为字节流：stream
	 * @throws IOException
	 */
	public static void testLoad_stream() throws IOException{
		
		Properties pro = new Properties();
		FileInputStream fis = new FileInputStream("D:\\test\\demo.txt");
		pro.load(fis);
		pro.list(System.out);
		fis.close();
		
	}
	
	/**
	 * 测试load方法，参数为字符流：reader
	 * @throws IOException
	 */
	public static void testLoad_reader() throws IOException{
		
		Properties pro = new Properties();
		FileReader fr = new FileReader("D:\\test\\demo.txt");
		pro.load(fr);
		pro.list(System.out);
		fr.close();
		
	}
	
	/**目的：对已有的配置文件中的信息进行修改。
	 * 	步骤：
	 * 		1、读取这个文件。
	 * 		2、并将这个文件中的键值数据存储到集合中。
	 * 		3、在通过集合对数据进行修改。
	 * 		4、在通过流将修改后的数据存储到文件中。 
	 */
	public static void test_save_update() throws IOException{
		testStore();
		File file = new File("D:\\test\\demo.txt");
		if(!file.exists()) file.createNewFile();
		
		Properties pro = new Properties();
		FileReader fr  =  new FileReader(file);
		pro.load(fr);
		
		FileWriter fw = new FileWriter(file); // TODO 注意：一定要在load方法调用以后，再新建输出字符流，否则，数据会丢失。
		pro.setProperty("wangwu", "88");
		pro.store(fw, "created by TestProperties.java");
		
		fw.close();
		fr.close();
		
	
	}
	
	public static Properties createDate(){
		Properties pro = new Properties();
		pro.put("zhangsan","22");
		pro.put("lisi","23");
		pro.put("wangwu","24");
		pro.put("zhaoliu","25");
		return pro;
	}

}
