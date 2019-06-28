package com.test.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author shiwei 2013-3-12 <br/>
 * 一些常见小问题的实现方式。
 * 		1.计算字符串中，每个字母出现的次数 。参照count 和 count2 方法。
 * 		2.在控制台打印如下图形：参照 print 方法
 * 					3				7
 * 				2		4		6		8
 * 			1				5				9
 * 
 * 		3.不使用string.substring，实现字符串截取功能。
 * 		4.拆分字符串“xxx-ttt-ffff”,不能使用一些可以直接拆分的API
 */
public class TestExam {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		count("abcdefdd张122");
//		count2("abcdefdd张122");
//		print();
//		printVideo(9);
		mysplit("xxx-ttt-ffff");
	}
	
	
	public static String mySubString(String str,int start,int end){
		String substr = new String(str.getBytes(),start,end);
		System.out.println(substr);
		return substr;
	}
	
	public static String[] mysplit(String str){
		
		List<String>list = new ArrayList<String>();
		
		char[] strchr = str.toCharArray();
		int start = 0;
		for(int i=0;i<strchr.length;i++){
			if(strchr[i]=='-'){
				list.add(new String(strchr,start,i-start));
				start=i+1;
			}
			if(i==strchr.length-1){
				if(strchr[i]!='-'){
					list.add(new String(strchr,start,strchr.length-start));
				}
			}
		}
		
		String []arrs = new String[list.size()];
		for(int i=0;i<list.size();i++){
			arrs[i] = list.get(i);
		}
		
		System.out.println(list);
		return arrs;
	}
	
	// TODO Exam1:
	// 获取字符串"abcdefddffeeaa"中，每个字母出现的次数。	
	// 推荐第二种方式。
	
	/**
	 * Exam1: 实现方式一：使用substring方法。
	 */
	public static void count(String str){
		Map<String, Integer>countMap = new TreeMap<String, Integer>(); 
		for(int i=0;i<str.length();i++){
			String subStr = str.substring(i,i+1);
			countMap.put(subStr, countMap.get(subStr)==null?1:(countMap.get(subStr)+1));
		}
		System.out.println(countMap);
	}
	
	/**
	 * Exam1: 实现方式二：使用toCharArray 方法，直接转换char类型的数组。<br/>
	 * 扩展：只记录字母的次数。
	 */
	public static void count2(String str){
		Map<Character, Integer>countMap = new TreeMap<Character, Integer>(); 
		char [] chs = str.toCharArray();
		
		System.out.println((int)('a')+","+(int)('A'));
		for(int i=0;i<chs.length;i++){
			
			if((chs[i]>='a' && chs[i]<='z') || (chs[i]>='A' && chs[i]<='Z')){
				countMap.put(chs[i], countMap.get(chs[i])==null?1:(countMap.get(chs[i])+1));
			}
			
		}
		System.out.println(countMap);
	}
	
	/**
	 * 要求：打印如下图形：
	 * 					3				7
	 * 				2		4		6		8
	 * 			1				5				9
	 * 
	 * 总结：一般图形的打印问题，都可以使用二维数组来解决。
	 * 	 1	a[2][0]
	 *   2	a[1][1]
	 *   3	a[0][2]
	 *   4	a[1][3]
	 *   5	a[2][4]
	 *   6	a[1][5]
	 *   7	a[0][6]
	 *   8	a[1][7]
	 *   9	a[2][8]
	 * 
	 * 	先分析规律：在图纸上画出如下图形：参照 【角标.jpg】
	 * 	只针对当前题目，找到规律：
	 * 						第一个下角标，出现的规律分别是2,1,0,1 
	 * 						第二个下角标，出现的规律是：0,1,2,3,4,5,6,7,8
	 *  思路：
	 *  	第二个角标，从0开始，不断自增。
	 *  	第一个角标，是不断循环的，循环的数组为： {2,1,0,1} ，循环的数组长度为4。只需要将当前的i对4取余数，就可找到循环数组loop对应的下角标，通过loop[index]找到值即可。
	 */
	public static void print(){
		int loop[]=new int[]{2,1,0,1};
		int a[][] = new int [3][9];
		int x = 2;
		int y = 0;
			for(int i=0;i<9;i++){
			 int index = i%4 ;
			 x = loop[index];
			 y = i;
			 a[x][y] = i+1;
			 
		}
			
		for(int i=0;i<a.length;i++){
			int b[] = a[i];
			for(int j=0;j<b.length;j++){
				System.out.print(b[j]==0?" ":b[j]);
			}
			System.out.println("\r\n");
		}
	}
	
	/**
	 * 自己做法：指定宽、高。宽就是打印的最大值，进行打印。
	 * 计算过程：
	 * 		height-1,height-2....1,0,1,2....height-2
	 * 		===>height+height-2
	 * @param width
	 * @param height
	 * @param max
	 */
	public static void printAny(int width,int height){
		int loop_length = 2*height-2;
		int loop[] = new int [2*height-2];
		for(int i=0;i<loop_length;i++){
			if(i<=height-1){
				loop[i]=height-i-1;
			}else{
				loop[i]=i-(height-1);
			}
		}
		int a[][] = new int[height][width];
		for(int i=0;i<width;i++){
			int index = i%(2*height-2);
			int x = loop[index];
			a[x][i] = i+1;
		}
		
		for(int i=0;i<a.length;i++){
			int b[] = a[i];
			for(int j=0;j<b.length;j++){
				System.out.print(b[j]==0?" ":b[j]);
			}
			System.out.println("\r\n");
		}
	}
	
	/**
	 * 视频的做法：
	 * 		思路：设置一个标识，标识为ture，则自增；否则，自减。--- 相对思路比较简单。
	 * @param num
	 */
	public static void printVideo(int num){
		int height = num/4+1;
		int width = num;
		int a[][] = new int [height][width];
		int x = height-1;
		int y=0;
		boolean flag = false;
		for(int i=1;i<=num;i++){
			a[x][y] = i;
			y++;
			if(!flag){
				x--;
			}else{
				x++;
			}
			if(x<0){
				flag = true;
				x=x+2;
			}
			if(x>height-1){
				flag = false;
				x=x-2;
			}
			
		}
		
		for(int i=0;i<a.length;i++){
			int b[] = a[i];
			for(int j=0;j<b.length;j++){
				System.out.print(b[j]==0?" ":b[j]);
			}
			System.out.println("\r\n");
		}
		
	}
}
