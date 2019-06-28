package com.test.common.test;

import java.io.IOException;

/**
 * 1、了解制表符的用法，参照demo1
 * 
 */
public class Demo {

	/**
	 * 
	 */
	public static void main(String[] args) throws IOException {
		
		demo1();
	}

	/**
	 * 两个要点：
	 * 		1. \t 是制表符，在打印表格的时候，可以使表格对齐。
	 * 		2. 打印九九乘法表。
	 * 
	 */
	public static void demo1(){
		
		for(int i=1;i<=9;i++){
			for(int j=1;j<=i;j++){
				System.out.print(i+"*"+j+"="+(i*j)+"\t");
			}
			System.out.println();
		}
	}
}
