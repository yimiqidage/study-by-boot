package com.test.basic;

import java.io.IOException;

/**
 * @author shiwei 2013-3-29 <br/>
 * 
 * 对于含有中文和英文字母的字符串，传入要截取的字符串长度，截取字符。例如：abc你好 ，不能出现乱码。
 * 
 * 测试截取字符串的问题：
 * 		首先：GB2312中，对于中文字符，编码都是负数，如：你-->-60 -29
 * 		但是：GBK中，对于中文字符，编码有可能是：第一个数为负数，第二个为正数。如汉字：琲，对应的GBK编码为： -84 105 
 * 		对于UTF-8编码的汉字，每个汉字对应3个字节。所有字节均为负数。需要另外处理。
 * 
 */
public class TestSplitByte {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String str = "abc你好def你好";
		String encode = "UTF-8";
		int len = str.getBytes(encode).length+1;
		for(int i=1;i<len;i++){
			System.out.println("截取"+i+"个字符串为："+splitByUTF8(str, i));
		}
		
		getByte("你");
	}
	
	/**
	 * 对于GBK编码的字符串进行截取。
	 * 		注意：虽然GBK编码对于有的文字，是一个负数，一个正数表示的，但是这个方法同样适用，不影响最后的结果。
	 * 
	 * @param str
	 * @param len
	 * @return
	 * @throws IOException
	 */
	public static String splitByGBK(String str ,int len) throws IOException{
		
		
		byte [] ch = str.getBytes("GBK");
		//注：count的作用是取出字节为负的个数，通过负字节的个数来判断，是否正好为N个汉字。
		int count = 0;
		for(int i = len-1;i>=0;i--){
			if(ch[i]<0){
				count++;
			}else{
				break;
			}
		}
		
		if(count%2==0) return new String(ch,0,len,"GBK");
		else return new String (ch,0,len-1,"GBK");
		
	}

	/**
	 *  对于UTF-8编码的字符串进行截取。
	 *  	注意：UTF-8每个文字对应3个字节。
	 * @param str
	 * @param len
	 * @return
	 * @throws IOException
	 */
	public static String splitByUTF8(String str ,int len) throws IOException{
		
		byte [] ch = str.getBytes("UTF-8");
		
		int count = 0;
		for(int i = len-1;i>=0;i--){
			if(ch[i]<0){
				count++;
			}else{
				break;
			}
		}
		
		//因为UTF-8每个中文对应的是3个字节，所有字节均为负数。所以，需要是3的倍数，才能取。如果不是，就舍。
		if(count%3==0) return new String(ch,0,len,"UTF-8");
		else if(count%3==1) return new String(ch,0,len-1,"UTF-8");
		else return new String (ch,0,len-2,"UTF-8");
		
		
		//TODO 实际上，上面的步骤可以合并成一句代码：
//		return new String (ch,0,len-(count%3),"UTF-8");
		
	}
	
	
	public static void getByte(String str ) throws IOException{
		byte[] ch = str.getBytes("GB2312");
		for(int i=0;i<ch.length;i++){
			System.out.print(ch[i]);
		}
	}
}
