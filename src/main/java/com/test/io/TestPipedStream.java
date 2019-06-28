package com.test.io;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @author shiwei 2013-3-29 <br/>
 * 管道流：比较有特点，但是不太常用。
 * 
 * PipedInputStream：管道输入流应该连接到管道输出流；管道输入流提供要写入管道输出流的所有数据字节。
 * PipedOutputStream：可以将管道输出流连接到管道输入流来创建通信管道。管道输出流是管道的发送端。
 * 
 * 管道流的使用方法演示：结合多线程使用，否则容易死锁。
 * 
 * 
 */
public class TestPipedStream {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		PipedInputStream input = new PipedInputStream();
		PipedOutputStream output = new PipedOutputStream();
		
		input.connect(output);  // 输入流和输出流进行连接
		
		new Thread(new Input(input)).start();
		new Thread(new Output(output)).start();
		
	}

}


class Input implements Runnable{
	
	private PipedInputStream in;
	Input(PipedInputStream in){
		this.in = in;
	}
	public void run(){
		
		try {
			byte[] buf = new byte[1024];
			int len = in.read(buf);
			
			String s = new String(buf,0,len);
			
			System.out.println("s="+s);
			in.close();
		} catch (Exception e) {
			
		}
		
	}
}

class Output implements Runnable{
	private PipedOutputStream out;
	Output(PipedOutputStream out){
		this.out = out;
	}
	public void run(){
		
		try {
			Thread.sleep(5000);
			out.write("hi，管道来了！".getBytes());
		} catch (Exception e) {
			
		}
	}
}


