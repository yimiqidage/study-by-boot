package com.test.io.stream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * @author shiwei 2013-3-26 <br/>
 * 介绍转换流： 
 * 		InputStreamReader:将字节流转换成字符流，进行读取动作。
 * 		OutputStreamWriter:将字符流转换成字节流，进行写入动作。
 * 
 * streamToReader()：使用InputStreamReader实现字节流转换成字符流。
 * streamToWriter(): 使用 OutputStreamWriter实现字符流转换成字节流。
 * streamToWriter_simple()：streamToWriter() 的简单写法。
 * 
 */
public class TestTransStream {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		streamToWriter();
	}

	
	/**
	 * 用另一种方式实现TestReadKey的演示二：读取从键盘输入的字母，并转换成大写字母输出；一旦用户输入over，结束键盘读入操作。<br/>
	 * 利用转换流实现。<br/>
	 * 实现字节流转换成字符流。
	 * @throws IOException
	 */
	public static void streamToReader() throws IOException{
		
		//TODO part1:字节流转换成字符流。
		
		//1、获得字节流
		InputStream in = System.in;
		//2、通过转换流将字节流转换成字符流。【字符流=字节流+编码表】
		InputStreamReader isr = new InputStreamReader(in);
		//3、利用缓冲字符流进行，进行输出。
		BufferedReader br = new BufferedReader(isr);
		
		String line=null;
		
		while((line=br.readLine())!=null){
			if("over".equalsIgnoreCase(line)){
				break;
			}
			System.out.println(line.toUpperCase());
		}
	}
	
	
	/**
	 * 实现字符流转换成字节流：使用 OutputStreamWriter
	 * @throws IOException
	 */
	public static void streamToWriter() throws IOException{
		
		//TODO part2:字符流转换成字节流。
		
		//1、获得字节流
		InputStream in = System.in;
		//2、通过转换流将字节流转换成字符流。【字符流=字节流+编码表】
		InputStreamReader isr = new InputStreamReader(in);
		//3、利用缓冲字符流进行，进行输出。
		BufferedReader br = new BufferedReader(isr);
		//4、获得输出流
		OutputStream out = System.out;
		OutputStreamWriter writer = new OutputStreamWriter(out);
		//5、获得缓冲区对象。
		BufferedWriter bw = new BufferedWriter(writer);
		String line=null;
		
		while((line=br.readLine())!=null){
			if("over".equalsIgnoreCase(line)){
				break;
			}
			bw.write(line.toUpperCase());
			bw.newLine();
			bw.flush(); //TODO 注意要刷新缓冲区
			
		}
	}
	
	/**
	 * streamToWriter 的简单写法。
	 * @see #streamToWriter
	 * @throws IOException
	 */
	public static void streamToWriter_simple() throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String line=null;
		
		while((line=br.readLine())!=null){
			if("over".equalsIgnoreCase(line)){
				break;
			}
			bw.write(line.toUpperCase());
			bw.newLine();
			bw.flush();
			
		}
	}
	
	
	
}
