package com.test.io.buffer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TestBufferedReader {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		read_better();
		read_best();
	}

	/**
	 * 整行读取：BufferedReader的方法。方便，简单。
	 * @throws FileNotFoundException
	 */
	public static void read_best() throws IOException {
		
		FileReader fr = new FileReader("D:\\demo.txt");
		
		BufferedReader br = new BufferedReader(fr);
		
		String line = null;
		while((line=br.readLine())!=null){
			System.out.println(line);
		}
		
		br.close();
	}

	/**
	 * 使用读取的缓冲区的方式：比使用fileReader.read(char[]ch) 好一点
	 * @throws IOException
	 */
	public static void read_better() throws IOException {
		
		FileReader fr = new FileReader("D:\\demo.txt");
		
		BufferedReader br = new BufferedReader(fr);
		
		char [] ch = new char[1024];
		int num = 0;
		while((num=br.read(ch))!=-1){
			System.out.println(new String(ch,0,num));
		}
		
		br.close();
	}
}
