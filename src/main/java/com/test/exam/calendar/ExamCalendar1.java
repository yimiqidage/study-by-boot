package com.test.exam.calendar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author shiwei 2013-3-22 <br/>
 * 题目：计算 "2012-3-17" 与 "2012-4-6" 中间间隔多少天？
 * 	思路：字符串的日期先转换成日期格式，再转换成毫秒值进行相减，最后获得天。
 *  bestMethod() 的实现，比badMethod() 要好。
 *  
 * 注意：compareTo方法：time1.compareTo(time2)，time1>time2,返回1，time1<time2，返回-1.相等，返回0
 */
public class ExamCalendar1 {

	/**
	 * 
	 */
	public static void main(String[] args) throws Exception{
		String dateString1 = "2012-3-17";
		String dateString2 = "2012-4-6";
		badMethod(dateString1,dateString2);
		bestMethod(dateString1,dateString2);
	}

	public static void bestMethod(String dateString1,String dateString2) throws Exception{
		Date date1 = parseDate(dateString1); //优势在于：使用给定的格式转换方式，而不是自己去转换。
		Date date2 = parseDate(dateString2);
		
		long abs = Math.abs(date1.getTime()-date2.getTime());//优势在于：不用判断大小，直接相减，获得绝对值。简单，明了。
		long date_gap = abs/1000/60/60/24;
		System.out.println("bestMethod:"+date_gap);
		
	}
	public static void badMethod(String date1,String date2){
		Calendar cal = Calendar.getInstance();
		
		String[] ymd1 = date1.split("-"); 
		cal.set(Integer.parseInt(ymd1[0]), Integer.parseInt(ymd1[1]), Integer.parseInt(ymd1[2]));
		Date time1 = cal.getTime();
		
		
		String[] ymd2 = date2.split("-"); 
		cal.set(Integer.parseInt(ymd2[0]), Integer.parseInt(ymd2[1]), Integer.parseInt(ymd2[2]));
		Date time2 = cal.getTime();
		
		long gap = (time1.after(time2)?(time1.getTime()-time2.getTime()):(time2.getTime()-time1.getTime()));
		long date_gap = gap/1000/60/60/24;
		System.out.println("badMethod:"+date_gap);
		
	}
	
	/**
	 * 另外一种转换日期的方式
	 * @param dateString
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDate(String dateString) throws ParseException{
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.parse(dateString);
	}
}
