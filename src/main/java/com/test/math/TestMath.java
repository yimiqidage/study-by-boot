package com.test.math;

/**
 * @author shiwei 2013-3-22 <br/>
 * 测试Math常用的方法：
 * 		ceil():返回大于参数的最小整数。
 * 		floor():返回小于参数的最大整数。
 * 		round():返回四舍五入的整数。
 * 		max(a,b):返回两个参数中，较大的那个。
 * 		pow(a,b):返回a的b次幂。
 * 		random():产生0-1的随机数。---> 参照TestRandom.java ，操作Random的对象。
 * 
 */
public class TestMath {

	/**
	 * 
	 */
	public static void main(String[] args) {
		
		double d1 = Math.ceil(12.56);
		double d2 = Math.floor(12.56);
		double d3 = Math.round(12.56);
		System.out.println("d1:"+d1);
		System.out.println("d2:"+d2);
		System.out.println("d3:"+d3);
	}

}
