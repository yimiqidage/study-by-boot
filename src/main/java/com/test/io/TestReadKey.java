package com.test.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author shiwei 2013-3-26 <br/>
 * 演示①：read()
 * 		演示键盘的录入动作，即从键盘输入值，然后读取，并显示。使用的是System.in。
 * 演示②：my_read(),video_read()
 * 		读取从键盘输入的字母，并转换成大写字母输出；一旦用户输入over，结束键盘读入操作。
 * 		
 */
public class TestReadKey {

	/**
	 * 可以直接在eclipse中执行，并且在控制台上输入任意字符，然后回车即可显示。
	 * 之所以会显示 13,10 是对应的 '\r' ,'\n' 的编码，就跟a的编码是97是一个道理。
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		video_read();
	}

	/**
	 * 读取键盘操作。注意：一旦关闭输入流以后，就无法再次获取了。
	 * @throws IOException
	 */
	public static void read() throws IOException{
		InputStream is = System.in;
		int ch = 0;
		while((ch=is.read())!=-1){
			System.out.println(ch);
		}
		// is.close();  // 这个方法最好不要使用，因为一旦关闭流以后，就不能新建了。
		// InputStream is2 = System.in; --> is2也是空的。
	}
	
	/**
	 * 自己的思路：读取从键盘输入的字母，并转换成大写字母输出；一旦用户输入over，结束键盘读入操作。
	 * @throws IOException
	 */
	public static void my_read() throws IOException{
		InputStream is = System.in;
		byte[] b = new byte[4];
		int ch = 0;
		while((ch=is.read(b))!=-1){
			String current = new String(b,0,ch);
			if("over".equalsIgnoreCase(current)) break;
			System.out.println(current.toUpperCase());
		}
	}
	
	/**
	 * 视频的做法：读取从键盘输入的字母，并转换成大写字母输出；一旦用户输入over，结束键盘读入操作。
	 * 思路：
	 * 1，因为键盘录入只读取一个字节，要判断是否是over，需要将读取到的字节拼成字符串。
	 * 2，那就需要一个容器。StringBuilder.
	 * 3，在用户回车之前将录入的数据变成字符串判断即可。 
	 * 
	 */
	public static void video_read() throws IOException{
		
		//1，创建容器。
		StringBuilder sb = new StringBuilder();
		
		//2，获取键盘读取流。		
		InputStream is = System.in;
		
		//3，定义变量记录读取到的字节，并循环获取。 
		int ch = 0;
		while((ch=is.read())!=-1){
			//在存储之前需要判断是否是换行标记 ,因为换行标记不存储。 
			if(ch=='\r') continue;
			if(ch=='\n') {
				String temp = sb.toString();
				if("over".equalsIgnoreCase(temp)){
					break;
				}
				System.out.println(sb.toString().toUpperCase());
				sb.delete(0, sb.length());
			}else {
				//将读取到的字节存储到StringBuilder中。
				sb.append((char)ch);
				
			}
		}
	}
	
}
