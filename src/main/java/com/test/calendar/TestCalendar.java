package com.test.calendar;

import java.util.Calendar;

/**
 * @author shiwei 2013-3-22 <br/>
 * Calendar类方法的注意点：
 * 		get(Calendar.MONTH); 即返回的月份，是以0开始的。
 * 		get(Calendar.DAY_OF_WEEK):返回值中，1代表星期日；2代表星期一.....7代表星期六。
 * 		get(Calendar.DAY_OF_WEEK_IN_MONTH): 返回当前日期，所在的周，在月中是第几周。1表示第一周。
 * 
 */
public class TestCalendar {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		test();
	}

	public static void test() throws Exception {
		Calendar cal = Calendar.getInstance();
		int y = cal.get(Calendar.YEAR);
		int m = cal.get(Calendar.MONTH);
		int d = cal.get(Calendar.DAY_OF_MONTH);
		int h = cal.get(Calendar.HOUR_OF_DAY);
		int mm = cal.get(Calendar.MINUTE);
		int s = cal.get(Calendar.SECOND);
		
		int w = cal.get(Calendar.DAY_OF_WEEK);
		int w2 = cal.get(Calendar.DAY_OF_WEEK_IN_MONTH);
		
		System.out.println(y+"年"+m+"月"+d+"日"+h+"时"+mm+"分"+s+"秒"+dayInCn(w)+","+w2);
	}
	
	public static String dayInCn(int dayOfWeek){
		String[]weeks = {"","星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
		return weeks[dayOfWeek];
	}
}
