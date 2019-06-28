package com.test.exam.io;

import java.io.File;

/**
 * @author shiwei 2013-3-28 <br/>
 * 练习一：遍历显示某个目录下的所有文件以及目录。
 * 		listAll(File file):	深度遍历文件[只遍历]：即显示文件目录下的所有文件+目录。
 * 		listAll(File file,int level)：深度遍历文件[添加层级目录]：即显示文件目录下的所有文件+目录。
 * 
 */
public class ExamFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File f = new File("D:\\test");
		listAll(f,0);
	}

	
	/**
	 * 深度遍历文件[只遍历]：即显示文件目录下的所有文件+目录。
	 * @param path
	 */
	public static void listAll(File file){
		System.out.println(file.getName());
		File[] fileList = file.listFiles();
		if(fileList.length>0){
			for(File f:fileList){
				if(f.isFile()){
					System.out.println(f.getName());
					continue;
				}
				listAll(f);
			}
		}
		
	}

	
	/**
	 * 深度遍历文件[添加层级目录]：即显示文件目录下的所有文件+目录。
	 * @param file
	 * @param level
	 */
	public static void listAll(File file,int level){
		level++;
		System.out.println(getSpace(level)+file.getName());
		File[] fileList = file.listFiles();
		if(fileList.length>0){
			for(File f:fileList){
				if(f.isFile()){
					System.out.println(getSpace(level)+f.getName());
					continue;
				}
				listAll(f,level);
			}
		}
		
	}

	public static String getSpace(int level) {
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<level;i++){
			sb.append("|--");
		}
		return sb.toString();
	}

}
