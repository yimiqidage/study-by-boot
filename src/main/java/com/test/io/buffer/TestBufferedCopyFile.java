package com.test.io.buffer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TestBufferedCopyFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
		FileReader fr =new FileReader("D:\\demo.txt");
		BufferedReader br = new BufferedReader(fr);
		
		FileWriter fw = new FileWriter("D:\\demo.txt");
		BufferedWriter  bw = new BufferedWriter(fw);
		
		String line = null;
		
		while((line=br.readLine())!=null){
			bw.write(line);
			bw.newLine(); //注意添加换行符。
			bw.flush(); //使用BufferedWriter 注意要经常刷新缓冲区。
		}
		
		bw.close();
		fw.close();
			
		
	}

}
