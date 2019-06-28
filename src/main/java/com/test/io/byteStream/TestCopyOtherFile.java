package com.test.io.byteStream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author shiwei 2013-3-26 <br/>
 * 测试拷贝其他格式的文件：如MP3格式。<br/>
 * 		提供了三种文件拷贝的方式：<br/>
 * 			①：自定义缓冲区，缓冲区大小一般为1024字节。<br/>
 * 			②：使用java提供的缓冲区方法：BufferedInputStream、BufferedOutputStream。建议使用这种方式。<br/>
 * 			③：调用fis.available() 方法。【该方法返回文件的字节大小】。然后建立一个该字节大小的缓冲区。非常不建议这种方式。<br/>
 * 注意： 字符流不能操作媒体文件等字节流数据，因为字符流在读取字节数据以后，会去查找字符表，如果没找到对应的字符，就会显示其他异常字符【可能是乱码】，最后再拷贝后，显示的有可能连大小都不一样。
 */
public class TestCopyOtherFile {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		copy_1();
		copy_2();
		copy_3();
		copy_4();
	}

	
	/**
	 * 拷贝方式一：自己定义字节的缓冲区。
	 * @throws IOException
	 */
	public static void copy_1() throws IOException {
		FileInputStream fis = new FileInputStream("D:\\0.mp3");
		FileOutputStream fos  = new FileOutputStream("D:\\1.mp3");
		
		byte[] b = new byte[1024];
		int len =0;
		while((len=fis.read(b))!=-1){
			fos.write(b, 0, len);
		}
		fos.close();
		fis.close();
		
	}

	/**
	 * 拷贝方式二：使用字节的缓冲区进行拷贝。 建议使用这种方式。
	 * @throws IOException
	 */
	public static void copy_2() throws IOException {
		FileInputStream fis = new FileInputStream("D:\\0.mp3");
		BufferedInputStream bfis = new BufferedInputStream(fis);
		
		FileOutputStream fos  = new FileOutputStream("D:\\2.mp3");
		BufferedOutputStream bfos = new BufferedOutputStream(fos);
		
		int len =0;
		while((len=bfis.read())!=-1){
			bfos.write(len);
		}
		bfos.close(); //注意调用的是缓冲区的关闭资源方法。
		bfis.close();
		
	}
	
	/**
	 * 拷贝方式三：根据文件的大小，直接建立一个和文件大小的缓冲区。不建议。如果文件很大，会内存溢出。
	 * 
	 * @deprecated
	 * @throws IOException
	 */
	public static void copy_3() throws IOException {
		FileInputStream fis = new FileInputStream("D:\\0.mp3");
		FileOutputStream fos  = new FileOutputStream("D:\\3.mp3");
		
		byte[] b = new byte[fis.available()];
		int len =0;
		while((len=fis.read(b))!=-1){
			fos.write(b, 0, len);
		}
		fos.close();
		fis.close();
		
	}
	
	/**
	 * 拷贝方式四：每次拷贝一个字节。 不推荐，效率极低。。
	 * @deprecated
	 * @throws IOException
	 */
	public static void copy_4() throws IOException {
		FileInputStream fis = new FileInputStream("D:\\0.mp3");
		FileOutputStream fos  = new FileOutputStream("D:\\4.mp3");
		
		int len =0;
		while((len=fis.read())!=-1){
			fos.write(len);
		}
		fos.close();
		fis.close();
		
	}
	
}
