package com.test.lock.deadLock.demo;

/**
 * @author shiwei 2013-3-20 <br/>
 * 作用就是创建两个锁
 * 
 */
public class MyLock {
	public static final Object locka = new Object();
	public static final Object lockb = new Object();
}
