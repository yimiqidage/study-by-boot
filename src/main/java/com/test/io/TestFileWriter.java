package com.test.io;


import java.io.FileWriter;
import java.io.IOException;

/**
 * @author shiwei 2013-3-22 <br/>
 * 使用FileWriter可以进行文件的操作，直接在文件中写入文字。<br/>
 * 操作字符，使用的是Writer的子类，FileWriter<br/>
 * 文件分隔符与路径分隔符的区别，参照TestSystem.java<br/>
 * filewriter.flush(): 进行刷新，将数据直接写到目的地中。<br/>
 * fliewriter.close(): 关闭流，关闭资源。在关闭前会先调用flush刷新缓冲中的数据到目的地。<br/>
 * 
 * 
 */
public class TestFileWriter {

	private static final String LINE_SEPARATOR = System.getProperty("line.separator");
	private static final String FILE_SEPARATOR = System.getProperty("file.separator");
	private static final String PATH_SEPARATOR = System.getProperty("path.separator");

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		stand();
		test();
		
		
	}
	
	
	/**
	 * 标准写法：记得关闭writer()
	 * @throws IOException
	 */
	public static void stand() throws IOException{
		FileWriter fw = null;
		try{
			fw = new FileWriter("D:"+PATH_SEPARATOR+"demo.txt",true);
			fw.write("abcde"+LINE_SEPARATOR+"hahaha");
			fw.close();
		}finally{
			if(fw!=null){ //一定要判断是否为null。因为假如fw在创建的时候，文件不存在，会抛出FileNotFoundException ，fw就会是null。
				try {
					fw.close();
				} catch (IOException e) {
					throw new RuntimeException(e.toString()); //对于无法关闭的情况，最好抛出去。
				}
			}
		}
	}
	
	/**
	 * 测试用的写法，注重过程。
	 * @throws IOException
	 */
	public static void test() throws IOException {
		//创建一个可以往文件中写入字符数据的字符输出流对象。
		/*
		 * 既然是往一个文件中写入文字数据，那么在创建对象时，就必须明确该文件(用于存储数据的目的地)。
		 * 
		 * 如果文件不存在，则会自动创建。
		 * 如果文件存在，则会被覆盖。
		 * 
		 * 如果构造函数中加入true，可以实现对文件进行续写！
		 */
		FileWriter fw = new FileWriter("D:"+PATH_SEPARATOR+"demo.txt",false);
		System.out.println("D:"+PATH_SEPARATOR+"demo.txt");
		System.out.println("D:"+FILE_SEPARATOR+"demo.txt");
		
		/*
		 * 调用Writer对象中的write(string)方法，写入数据。 
		 * 
		 * 其实数据写入到临时存储缓冲区中。
		 * 
		 */
		fw.write("abcde"+LINE_SEPARATOR+"hahaha");
		
		/*
		 * 进行刷新，将数据直接写到目的地中。
		 */
		
		fw.close();
		
//		fw.write("haha");// java.io.IOException: Stream closed
	}

}