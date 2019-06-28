package com.test.basic;

/**
 * @author shiwei 2013-3-28 <br/>
 * 基础知识补充：
 * 演示递归的用法:将一个十进制转换成二进制。【可以参照  递归1.bmp】.
 * 根据进栈出栈的顺序，可以理解打印的结果原因。
 * 		注意 toBin_1() 和 toBin_2() 的区别：
 * 			 toBin_1()：打印的顺序是反向打印的，打印的结果是： 1 1 0
 * 			 toBin_2(): 打印的顺序是正向打印的，打印的结果是： 0 1 1 
 * 但是，递归耗费内存，例如getSum_1(9000),就会提示栈内存溢出，所以要慎用。
 */
public class TestDigui {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		toBin_1(6);
		System.out.println("---------");
		toBin_2(6);
		
		int s = getSum_1(100);
		System.out.println(s);
		System.out.println(getSum_2(9000));
	}

	/**
	 * 演示递归：将一个十进制数，转换成二进制。<br/>
	 * 进栈顺序： toBin_1(6) ---> toBin_1(3) ---> toBin_1(1) ---> toBin_1(0)<br/>
	 * 由进栈出栈顺序可知：最先出栈的是 toBin_1(0) ,但是因为不符合 (0>0)，所以最先出栈。<br/>
	 * 然后是 toBin_1(1)，所以执行的是System.out.println(1%2); --> sop(1)<br/>
	 * 然后是 toBin_1(3)，所以执行的是System.out.println(3%2); --> sop(1)<br/>
	 * 然后是 toBin_1(6)，所以执行的是System.out.println(6%2); --> sop(0)<br/>
	 * 所以打印结果是 1 1 0 <br/>
	 * @param b
	 */
	public static void toBin_1(int b){
		if(b>0){
			toBin_1(b/2);
			System.out.println(b%2);
		}
	}
	
	
	/**
	 * 演示递归：将一个十进制数，转换成二进制。<br/>
	 * 进栈出栈顺序同toBin_1() 方法一致，但是因为先执行打印语句，所以结果是 0 1 1 <br/>
	 * @param b
	 */
	public static void toBin_2(int b){
		if(b>0){
			System.out.println(b%2);
			toBin_2(b/2);
		}
	}
	
	
	public static int getSum_1(int n){
		if(n==1) return 1;
		return n + getSum_1(n-1);
	}
	
	
	public static int getSum_2(int n){
		int sum = 0;
		for(int i=1;i<=n;i++){
			sum=sum+i;
		}
		return sum;
	}
}
