package com.test.io;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author shiwei 2013-3-29 <br/>
 * RandomAccessFile 随机访问文件。<br/>
 * 
 * 注意：构造函数的mode可选值为："r","rw","rws","rwd" [参照jdk文档]<br/>
 * 
 * 特点：<br/>
 * 1，该对象即能读，又能写。<br/>
 * 2，该对象内部维护了一个byte数组，并通过指针可以操作数组中的元素，<br/>
 * 3，可以通过getFilePointer方法获取指针的位置，和通过seek方法设置指针的位置。<br/>
 * 4，其实该对象就是将字节输入流和输出流进行了封装。 <br/>
 * 5，该对象的源或者目的只能是文件。通过构造函数就可以看出。 <br/>
 * 
 * 
 */
public class TestRandomAccessFile {


	public static void main(String[] args) throws IOException {
		writeFile();
		readFile();
	}
	
	/**
	 * 
	 * 按照字符顺序，存储文件。[注意，演示的存储，姓名和年龄都是占用了4个字节。]使用GBK编码，一个中文2个字节，所以是4个字节。而writeInt方法，是每个数字占用4个字节存储。
	 * 
	 * @throws IOException
	 */
	public static void writeFile() throws IOException{
		
		RandomAccessFile ras = new RandomAccessFile("D:\\test\\random.txt", "rw"); //mode为rw，可以读取，写入。
		
		ras.write("张三".getBytes());
		
//		ras.write(609); //write(int i) ：会丢弃最高的八位，只在硬盘上存入后八位，会丢失数据，所以应该调用writeInt
		
		ras.writeInt(609); //写入int类型数据，使用这个方法，因为它是 按四个字节将 int 写入该文件，先写高字节。每个int占4个字节。
		
		ras.write("李四".getBytes());
		ras.writeInt(99);
		
		ras.close();
	}
	
	/**
	 * 读取有固定格式的文件。<br/>
	 * seek方法可以设置当前读取的角标位置，也就是可以指定读取位置。<br/>
	 * 
	 * @throws IOException
	 */
	public static void readFile() throws IOException{
		
		RandomAccessFile ras = new RandomAccessFile("D:\\test\\random.txt", "r");
		byte [] ch = new byte[4];
		ras.read(ch);
		
		String name = new String(ch); //因为姓名张三，占用4个字节，所以先读取四个字节。
		int age = ras.readInt();	//年龄占用四个，所以再次读取4个字节。
		
		System.out.println("name:"+name);
		System.out.println("age:"+age);
		
		
		//如果想直接从第二个人开始读取，可以直接设置下角标。-->因为内部是数组，直接设置数组角标。
		
		ras.seek(8); //角标也是从0开始。
		ras.read(ch);
		String name2 = new String(ch);
		
		int age2 = ras.readInt();
		
		System.out.println("name2:"+name2);
		System.out.println("age2:"+age2);
		
		//通过以上步骤，可以发现，如果采用这种方式存取数据，要求数据比较有规律性，否则无法读取数据。
		//例如：如果想存取姓名，则可以用固定的16个字节存储姓名[不够的用空格补充],然后用4个字节存储年龄。这样，每个人就是占用固定的20个字节。
		
	}

}
