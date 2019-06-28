package com.test.math.random;

import java.util.Random;

/**
 * @author shiwei 2013-3-22 <br/>
 *  random.nextInt(10):返回小于10的随机整数。
 * 
 */
public class TestRandom {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Random r = new Random();
		for (int i = 0; i < 10; i++) {
			System.out.println(r.nextInt(10));
		}
	}

	
}
