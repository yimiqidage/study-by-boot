package com.test.io;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author shiwei 2013-3-25 <br/>
 * 文件拷贝练习
 * 
 */
public class TestFileCopy {

	private static final int BUFFER_SIZE = 1024;

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		copy("D:", "E:", "demo.txt");

	}

	/**
	 * 简单的文件拷贝实现
	 * @param from
	 * @param to
	 * @param fileName
	 * @throws IOException
	 */
	public static void copy(String from,String to,String fileName) throws IOException{
		
		FileReader fr =null;
		FileWriter fw = null;
		
		try{
			fr = new FileReader(from+File.separator+fileName);
			fw =  new FileWriter(to+File.separator+fileName);
			char[] ch = new char[BUFFER_SIZE];
			int len = 0;
			while((len = fr.read(ch))!=-1){
				fw.write(ch, 0, len); // write(char[] cbuf, int off, int len)方法：给定字符串数组，从off开始读取，读取的长度为len。 
			}
			
		}finally{ //TODO 记得在finally里面关闭流。
			if(fw!=null){
				fw.close();
			}
			if(fr!=null){
				fr.close();
			}
		}
		
	}
}
