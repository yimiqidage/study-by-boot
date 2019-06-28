package com.test.regex;

import java.util.TreeSet;

/**
 * @author shiwei 2013-4-1 <br/>
 * 使用正则表达式进行练习：
 * 		1、治疗口吃:我我...我我...我我我要...要要要要...要要要要..学学学学学...学学编编...编编编编..编..程程...程程...程程程
 * 		2、对ip地址排序。 
 * 		3、对邮件地址校验。
 * 
 */
public class TestRegexDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		test_2();
	}

	/**
	 * 治疗口吃:过滤重复字符
	 */
	public static void test_1(){
		
		String str = "我我...我我...我我我要...要要要要...要要要要..学学学学学...学学编编...编编编编..编..程程...程程...程程程";
		String retStr = null;
		str = str.replaceAll("\\.", "");
		retStr = str.replaceAll("(.)\\1+", "$1");
		System.out.println(retStr);
	}
	
	/**
	 * ip地址排序：192.168.10.34 127.0.0.1 3.3.3.3  105.70.11.55
	 */
	public static void test_2(){
		
		String str = "192.168.10.34 127.0.0.1 3.3.3.3  105.70.11.55";
		
		//1、给所有的ip分段补零。每个分段补两个零。 00192.00168.0010.0034 00127.000.000.001 003.003.003.003  00105.0070.0011.0055
		str = str.replaceAll("(\\d{1,3})+", "00$1");
		//2、将所有的分段，都截成3位。192.168.010.034 127.000.000.001 003.003.003.003  105.070.011.055
		str = str.replaceAll("0+([0-9]{3})", "$1");
		//3、将字符串分段
		String [] ips = str.split(" +");
		//使用TreeSet对字符串继续行排序
		TreeSet<String> ts = new TreeSet<String>();
		
		//如果想进行逆序显示，可以使用下列方式
//		TreeSet<String> ts = new TreeSet<String>(Collections.reverseOrder());
		
		for(String ip:ips){
			ts.add(ip);
		}
		
		for(String ip:ts){
			//4、显示的时候，将ip地址中，各分段前面的零，去掉。192.168.10.34 127.0.0.1 3.3.3.3  105.70.11.55
			System.out.println(ip.replaceAll("0*(\\d+)", "$1"));
		}
		
	}
	/**
	 * 对邮件地址校验。
	 */
	public static void test_3(){
		
		String str = "abc_@sohu.com.cn";
		
		//[a-zA-Z0-9_]+ ： 至少有一个字母或者数字；(\\.[a-zA-Z]{1,3})+ ：代表域名，至少有一个 如：.com , .com.cn
		String regex = "[a-zA-Z0-9_]+@[a-zA-Z0-9]+(\\.[a-zA-Z]{1,3})+";
		boolean b  = str.matches(regex);
		
		System.out.println(b);
		
		
		
	}
}
