package com.test.io;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author shiwei 2013-3-24 <br/>
 * 简单的文件读取，使用FileReader。
 * 明确： IOException是FileNotFoundException的父类，抛出IOException，就包括了FileNotFoundException。
 *  fileReader.read() 方法总结：
 * 		①：读取字符串时，返回的是int类型的，如：字母a返回的是97。所以，需要转换成字符，即 char 类型。
 * 		②：并且 fileReader.read()方法的返回值就是 0 到 65535 之间 (0x00-0xffff)，如果已到达流的末尾，则返回 -1 。所以可以用是不是-1来判断是否已经到达文件的结尾。
 * 
 * fr.read():返回的是读取的当前字符的编码值。
 * fr.read(char[]ch)：返回的是读取的字符数，如果已到达流的末尾，则返回 -1 
 */
public class TestFileReader {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		readMethod1();
//		process2();
	}
	
	

	/**
	 * 读取方式一: 每次读取一个字符，并打印。使用fileReader.read(); 方法。
	 * @throws IOException
	 */
	public static void readMethod1() throws IOException{
		
		FileReader fr = null;
		try{
			fr = new FileReader("/Users/weishi8/D/test/delete-table.txt");
			int ch = 0;
			while((ch=fr.read())!=-1){ // TODO 只要读取到的字符不是-1，就说明没有到文件结尾，就可以继续向下读。
				System.out.println((char)ch);
			}
			
		}finally{
			if(fr!=null)
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
					throw new RuntimeException(e.toString());
				}
		}
	}
	
	/**
	 * 测试读取方法fr.read(char[] ch);
	 * @throws IOException
	 */
	public static void readMethod2()  throws IOException{
		
		writeForRead();
		
		FileReader fr = new FileReader("D:\\demo.txt");
		char[] ch = new char[3]; // TODO 长度最好为1024[也就是千字节]的整数倍。
		int len = 0;
		while((len=fr.read(ch))!=-1){
			System.out.println(new String(ch,0,len)); // 注意使用String的构造函数：String(char[] value, int offset, int count) 
		}
		
		
	}
	
	/**
	 * 读取read(char[]) 的动作分解，
	 * 	注意结果为：
	 * 			3:abc
	 *  		2:dec
	 *  		-1:dec
	 *  也就是说，读取的时候，会从下标为0，开始覆盖，如果char数组的长度为3，但是只有2个字符了，那么，第三个字符是不会被覆盖的，还是原来读取到的。
	 * @throws IOException
	 */
	public static void process2() throws IOException{
		
		writeForRead();
		
		FileReader fr = new FileReader("D:\\demo.txt");
		char[] ch = new char[3]; // TODO 长度最好为1024[也就是千字节]的整数倍。
		int num = fr.read(ch);
		System.out.println(num+":"+new String(ch));
		
		int num1 = fr.read(ch);
		System.out.println(num1+":"+new String(ch));
		
		int num2 = fr.read(ch);
		System.out.println(num2+":"+new String(ch));
		
	}
	
	/**
	 * 为了测试读取方法，设置。
	 * @throws IOException
	 */
	public static void writeForRead() throws IOException{
		FileWriter fw = new FileWriter("D:\\demo.txt");
		fw.write("abcde");
		fw.close();
	}
}
