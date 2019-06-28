package com.test.io.print;

import java.io.IOException;
import java.io.PrintStream;

/**
 * @author shiwei 2013-3-28 <br/>
 * 演示PrintStream对象的使用
 * 
 */
public class TestPrintStream {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		write_print();
	}

	
	public static void write_print() throws IOException{
		
		PrintStream ps = new PrintStream("D:\\test\\print.txt");
		
		ps.write(97); //存储以后，会变成字母a。
		ps.write(610);//存储以后，会变成字母b。因为只保存二进制的最低8位。
		ps.print(97); //内部封装的方法就是先将数字转换成字符串，再调用write方法。
		ps.close();
	}
}
