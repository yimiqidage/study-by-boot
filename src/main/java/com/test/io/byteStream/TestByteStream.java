/**
 * 
 */
package com.test.io.byteStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author shiwei 2013-3-26 <br/>
 * 测试的类：FileInputStream , FileOutputStream
 * 测试字节流：文件输入流[文件读取的多种方式]、文件输出流。
 * 		文件读取的两种方式：①：单个字节读取。②：建立一个字节缓冲区，读取。 --- 建议使用第二种方式。
 *
 * 
 */
public class TestByteStream {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		write();
		read1();
	}

	/**
	 * 字节流读取对象：一般使用具有缓冲区的字节数组。也就是第二种读取方式。
	 * @throws IOException
	 */
	public static void read1() throws IOException{
		
		//创建一个读取流对象。和指定文件关联。
		FileInputStream fis = new FileInputStream("D:\\demo.txt");
		
		//读取方式一,每次读取一个字节。
//		int r = 0;
//		while((r = fis.read())!=-1){
//			System.out.println((char)r);
//		}
		
		
		//读取方式二，使用字节缓冲区，跟字符缓冲区是类似的。只不过在字符流中，缓冲区是char[]数组，字节流中，是byte[]数组。
		// 建议使用这种读取数据的方式
		int len = 0;
		byte[] b = new byte[1024];
		while((len = fis.read(b))!=-1){
			System.out.println(new String(b,0,len));
		}
		
	}
	
	
	/**
	 * 字节流写入对象
	 * @throws IOException
	 */
	public static void write() throws IOException{
		FileOutputStream fos = new FileOutputStream("D:\\demo.txt");
		fos.write("abced张三".getBytes());
		fos.flush(); //这个方法可以省略，因为通过源码可知，FileOutputStream自己没有实现flush方法，调用的是父类OutputStream的flush方法，
					 //但是OutputStream的flush方法为空。所以，调用和不调用效果是一样的。
		fos.close(); // 关闭资源动作要完成。
	}
}
