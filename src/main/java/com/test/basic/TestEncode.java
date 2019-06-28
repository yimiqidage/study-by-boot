package com.test.basic;

import java.io.IOException;

/**
 * @author shiwei 2013-3-29 <br/>
 * 测试编码问题：
 * 
 * 		ISO8859-1 ：是欧洲编码表标准，每个文字对应一个字节，所以，不支持中文。
 * 		GB2312：每个汉字对应2个字节表示，但是包含的汉字没有GBK编码完全。所有编码都是负数。如：你--> -60 -29 [对应的编码]。
 * 		GBK：每个汉字对应的是2个字节表示。因为包含更多的汉字，所以会出现一正一负的情况，如：琲--> -84 105 
 * 		UTF-8：每个汉字对应的是3个字节表示。所有字节均为负数。
 */
public class TestEncode {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		encode("ISO8859-1");
		encode("UTF-8");
	}

	/**
	 * 结论：ISO8859-1 是单字节编码的，即使查不到对应的字符，也不会修改原来的字节码。
	 * UTF-8 是三个字节对应一个中文字符，如果找不到，会使用问号[-17 -65 -67]代替原来的字节，也就是修改了字节码。所以，再解析，就会字节码就变化了，解码失败。
	 * @throws IOException
	 */
	public static void encode(String code) throws IOException{
		
		
		String str = "你好";
		
		byte[] buf = str.getBytes("GBK");
		
		String s1 = new String(buf,code);
		
		System.out.println("s1="+s1);
		
		
		byte[] buf2 = s1.getBytes(code);//获取源字节.
		
		printBytes(buf2);
		
		String s2 = new String(buf2,"GBK");
		
		System.out.println("s2="+s2);
	}
	
	/**
	 * @param str
	 * @throws UnsupportedEncodingException
	 */
	public static void encodeDemo(String str) throws IOException{
		
		//编码；
		byte[] buf = str.getBytes("UTF-8");
		
		
		//解码：
		String s1 = new String(buf,"UTF-8");
		
		System.out.println("s1="+s1);
	}

	private static void printBytes(byte[] buf) {
		for(byte b : buf){
			System.out.print(b +" ");
		}
	}

}
