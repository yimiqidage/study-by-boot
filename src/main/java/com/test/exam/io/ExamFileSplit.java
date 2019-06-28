/**
 * 
 */
package com.test.exam.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.test.io.stream.TestSequenceInputStream;

/**
 * @author shiwei 2013-3-28 <br/>
 * 需求：文件分割。将一个文件分割成多个，大小为2M的文件。<br/>
 * 		splitFile(): 输入流为一个，输出流为多个。<br/>
 * 
 */
public class ExamFileSplit {

	private static final int SIZE = 1024*10;


	/**
	 * 演示(文件->分隔-->合并)：<br/>
	 * 		1、先利用 splitFile() 方法对文件切割成指定大小的文件；<br/>
	 * 		2、然后再将其进行合(调用TestSequenceInputStream.execute()方法)，并且输出到指定目录下；<br/>
	 * 		3、最后演示合并后的文件是否完整。<br/>
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		File outFilePath = new File("D:\\test");
		File file = new File("D:\\test\\Book1.xls");
		List<File> files = splitFile(file,outFilePath);
		
		outFilePath = new File("D:\\test\\合并以后");
		TestSequenceInputStream.merge(files, outFilePath,"Book1.xls"); 
	}

	/**
	 * 对文件按照指定大小进行切割。
	 * @param file 要切割的文件
	 * @param outFilePath 切割后文件的输出路径
	 * @throws IOException
	 */
	public static List<File> splitFile(File file,File outFilePath) throws IOException{
		
		List<File> files = new ArrayList<File>();
		
		String fileName = file.getName();
		String fileName_except_sufix = fileName.split("\\.")[0];
		
		//设置切割后，文件输出的路径
		int part = 1;
		FileInputStream fis = new FileInputStream(file);
		FileOutputStream fos = null;
		
		byte [] buf = new byte[SIZE];
		int len = 0;
		while((len=fis.read(buf))!=-1){
			String fname = fileName_except_sufix+"_"+part+".part";
			File currFile = new File(outFilePath,fname);
			fos = new FileOutputStream(currFile);
			fos.write(buf,0,len);
			part++;
			
			files.add(currFile);
		}
		fos.close(); //只需要在最后关闭即可，因为最后只有一个输出流。
		fis.close();
		
		return files;
	}
}
