package com.test.exam.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


/**
 * @author shiwei 2013-3-26 <br/>
 * IO操作的一些相关问题：<br/>
 * 		①：文件读写的相关操作：fileReadAndWrite()<br/>
 * 			①-1：将键盘录入的数据输入到一个文件中。<br/>
 * 			①-2：将一个文本内容显示到控制台上。<br/>
 * 			①-3：将一个文件的内容复制到另一个文件中。<br/>
 * 
 * 		②：将中文转换成指定编码的文件进行存储。fileReadAndWrite_charset_write()<br/>
 * 		③：读取含有中文的指定编码格式的文件，并显示。示例为UTF-8编码。fileReadAndWrite_charset_read()<br/>
 * 
 * 概述：涉及到字符编码的类为两个转换类： InputStreamReader,OuputStreamWriter
 * 		记忆思路：涉及到编码，肯定跟字符相关：只有两个过程：1.读取过程：字节转换成字符[InputStreamReader]。2.写入过程，字符转换成字节[OuputStreamWriter]。
 * 
 */			
public class ExamIO {

	public static void main(String[] args) throws IOException {
		fileReadAndWrite_charset_write();
		fileReadAndWrite_charset_read();
	}
	
	/**
	 * 只需要修改相应的输入流，输出流，即可实现不同的需求。
	 * @throws IOException
	 */
	public static void fileReadAndWrite() throws IOException{
		
		//①-1：将键盘录入的数据输入到一个文件中。
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\demo2.txt")));
		
		//①-2：将一个文本内容显示到控制台上。
//		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\demo.txt")));
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));		
		
		//①-3：将一个文件的内容复制到另一个文件中。
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\demo.txt")));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\demo3.txt")));
		
		String line=null;
		
		while((line=br.readLine())!=null){
			if("over".equalsIgnoreCase(line)){
				break;
			}
			bw.write(line.toUpperCase());
			bw.newLine();
			bw.flush(); //TODO 注意 要刷新
			
		}
	}
	
	/**
	 * 将中文转换成指定编码的文件进行存储。示例为UTF-8
	 * @throws IOException
	 */
	public static void fileReadAndWrite_charset_write() throws IOException{
		
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\demo_charset_UTF8.txt"),"UTF-8"));
		
		bw.write("你好");
		
		bw.close();
		
		
	}
	
	/**
	 * 读取含有中文的指定编码格式的文件，并显示。示例为UTF-8编码。
	 * @throws IOException
	 */
	public static void fileReadAndWrite_charset_read() throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\demo_charset_UTF8.txt"),"GBK"));
		
		String line = null;
		
		while((line = br.readLine())!=null){
			System.out.println(line);
		}
		
		br.close();
		
	}
}

