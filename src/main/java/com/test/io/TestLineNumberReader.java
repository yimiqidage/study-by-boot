package com.test.io;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;

/**
 * @author shiwei 2013-3-24 <br/>
 * 简单演示LineNumberReader的用法：添加了行号。可以使用setLineNumber和getLineNumber方法
 * 
 */
public class TestLineNumberReader {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		readMethod();
	}

	/**
	 * @throws IOException
	 */
	public static void readMethod() throws IOException{
		
		LineNumberReader lnr = null;
		FileReader fr = null;
		try{
			fr = new FileReader("/Users/weishi8/D/test/delete-table.txt");
			lnr = new LineNumberReader(fr);
			String line = null;
			while((line=lnr.readLine())!=null){
				System.out.println(lnr.getLineNumber()+":"+line);
			}
			
		}finally{
			if(lnr!=null)
				try {
					lnr.close();
				} catch (IOException e) {
					e.printStackTrace();
					throw new RuntimeException(e.toString());
				}
		}
	}
	
	
	
	/**
	 * 为了测试读取方法，设置。
	 * @throws IOException
	 */
	public static void writeForRead() throws IOException{
		FileWriter fw =  new FileWriter("D:\\demo.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("abcde");
		bw.newLine();
		bw.write("def");
		bw.close();
	}
}
