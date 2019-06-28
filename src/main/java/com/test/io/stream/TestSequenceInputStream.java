/**
 * 
 */
package com.test.io.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

/**
 * @author shiwei 2013-3-28 <br/>
 * 
 * 序列流：SequenceInputStream 表示其他输入流的逻辑串联。它从输入流的有序集合开始，并从第一个输入流开始读取，直到到达文件末尾，接着从第二个输入流读取，依次类推，直到到达包含的最后一个输入流的文件末尾为止。
 * 
 * 演示SequenceInputStream的用法：<br/>
 * 
 * 需求：将1.txt 2.txt 3.txt文件中的数据合并到一个文件中。<br/>
 * 
 * 视频地址：[JavaSE基础视频23\IO流\50-IO流(序列流-SequenceInputStream).avi 、 51-IO流(序列流-SequenceInputStream-枚举和迭代).avi]<br/>
 * 
 */
public class TestSequenceInputStream {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		demo_1();
		demo_2();
		demo_3();
		
	}

	/**
	 * 不推荐：[enumeration 已经被迭代器取代]<br/>
	 * 解决办法一：使用构造函数 SequenceInputStream(Enumeration<? extends InputStream> e)来解决。<br/>
	 * 		因为参数是枚举类型，而Vector对象的 elements() 方法可以返回枚举对象，所以，使用vector对象来添加不同的输入流。<br/>
	 * @throws IOException
	 */
	public static void demo_1 ()throws IOException {
		
		Vector<FileInputStream> vs = new Vector<FileInputStream>();
		vs.add(new FileInputStream("D:\\test\\1.txt"));
		vs.add(new FileInputStream("D:\\test\\2.txt"));
		vs.add(new FileInputStream("D:\\test\\3.txt"));
		
		Enumeration<FileInputStream> es = vs.elements();
		SequenceInputStream sis = new SequenceInputStream(es);
		
		FileOutputStream fos = new FileOutputStream("D:\\test\\4.txt");
		
		byte [] buf = new byte[1024];
		
		int len = 0;
		
		while((len=sis.read(buf))!=-1){
			fos.write(buf,0,len);
		}
		
		fos.close();
		sis.close();
	}
	
	/**
	 * 使用构造方法：SequenceInputStream(InputStream s1, InputStream s2) ，但只能连接两个不同的流。有局限性。<br/>
	 * @throws IOException
	 */
	public static void demo_2() throws IOException {
		
		FileInputStream f1 = new FileInputStream("D:\\test\\1.txt");
		FileInputStream f2 = new FileInputStream("D:\\test\\2.txt");
		
		SequenceInputStream sis = new SequenceInputStream(f1,f2);
		
		FileOutputStream fos = new FileOutputStream("D:\\test\\5.txt");
		
		byte [] buf = new byte[1024];
		
		int len = 0;
		
		while((len=sis.read(buf))!=-1){
			fos.write(buf,0,len);
		}
		
		fos.close();
		sis.close();
		
	}
	
	/**
	 * 推荐：<br/>
	 * 解决办法二：
	 * 使用list转换成枚举enumeration<br/>
	 * 因为Vector读取速度慢，已经被list取代，所以建议使用List。<br/>
	 * 		思路：因为list中没有方法返回枚举类型，所以，思考工具类Collections中是否有返回枚举类型的方法，可以找到Collections.enumeration(Collection c)符合。<br/>
	 * @throws IOException
	 */
	public static void demo_3 ()throws IOException {
		
		List<FileInputStream> fs = new ArrayList<FileInputStream>();
		fs.add(new FileInputStream("D:\\test\\1.txt"));
		fs.add(new FileInputStream("D:\\test\\2.txt"));
		fs.add(new FileInputStream("D:\\test\\3.txt"));
		
		Enumeration<FileInputStream> es = Collections.enumeration(fs);
		
		SequenceInputStream sis = new SequenceInputStream(es);
		
		FileOutputStream fos = new FileOutputStream("D:\\test\\6.txt");
		
		byte [] buf = new byte[1024];
		
		int len = 0;
		
		while((len=sis.read(buf))!=-1){
			fos.write(buf,0,len);
		}
		
		fos.close();
		sis.close();
	}
	
	/**
	 * 合并传入的多个流，并且输出到指定目录下
	 * @param files 传入的多个分割的文件
	 * @param outFilePath 合并以后的文件的路径
	 * @throws IOException
	 */
	public static void merge(List<File>files ,File outFilePath,String fileName)throws IOException {
		
		if(!outFilePath.exists()) outFilePath.mkdirs();
		
		List<FileInputStream> fs = new ArrayList<FileInputStream>();
		for(File file:files){
			fs.add(new FileInputStream(file));
		}
		
		Enumeration<FileInputStream> es = Collections.enumeration(fs);
		
		SequenceInputStream sis = new SequenceInputStream(es);
		
		FileOutputStream fos = new FileOutputStream(new File(outFilePath,fileName));
		
		byte [] buf = new byte[1024];
		
		int len = 0;
		
		while((len=sis.read(buf))!=-1){
			fos.write(buf,0,len);
		}
		
		fos.close();
		sis.close();
	}
}
