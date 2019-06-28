package com.test.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author shiwei 2013-3-29 <br/>
 * 操作基本java对象的流对象。
 * 
 */
public class TestDataSteam {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		writeData();
//		readData();
		
	}

	public static void readData() throws IOException {
		
		DataInputStream dis = new DataInputStream(new FileInputStream("D:\\test\\data.txt"));
		
		String str = dis.readUTF();
		
		System.out.println(str);
	}

	/**
	 * 有个小细节：因为UTF-8是每个中文占用3个字节，正常情况下，存储以后，应该是6个字节。
	 * 但是因为使用的是UTF-8修改版，存储的时候，会带着UTF-8编码的标字头，会占用两个字节。所以正常的字节大小应该:中文个数x3 + 2.
	 * 即：输入'你好你好'，最后大小是14个字节。
	 * @throws IOException
	 */
	public static void writeData() throws IOException {
		
		DataOutputStream dos = new DataOutputStream(new FileOutputStream("D:\\test\\data.txt"));
		
		dos.writeUTF("你好"); //使用的是UTF8 修改版存储。
		
		dos.close();
		
		
	}

}
