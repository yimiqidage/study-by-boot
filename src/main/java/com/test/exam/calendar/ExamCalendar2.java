package com.test.exam.calendar;

import java.util.Calendar;

/**
 * @author shiwei 2013-3-22 <br/>
 * 题目：判断任何一年的2月，有多少天  。
 * 		思路一：用年份除以四，取余数。为零，就是29天；
 * 		思路二：获得Calendar，并且设置成 year -03-01 ，然后，减一天，得到时间的day。
 * 
 */
public class ExamCalendar2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		method2(2011);
	}
	
	public static void method2(int y){
		Calendar cal = Calendar.getInstance();
		cal.set(y,2,1); //注意：2代表3月份。----> 代表对应年份的3月1日。
		cal.add(Calendar.DAY_OF_MONTH, -1);
		int num = cal.get(Calendar.DAY_OF_MONTH);
		System.out.println(num);
		
	}

}
