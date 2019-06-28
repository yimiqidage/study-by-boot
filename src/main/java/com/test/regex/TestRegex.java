package com.test.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//^[a-z]+$　　//匹配由26个英文字母的小写组成的字符串
//^[A-Z]+$　　//匹配由26个英文字母的大写组成的字符串
//^[A-Za-z]+$　　//匹配由26个英文字母组成的字符串
//^[A-Za-z0-9]+$　　//匹配由数字和26个英文字母组成的字符串
//^\w+$　　//匹配由数字、26个英文字母或者下划线组成的字符串
//[\u4e00-\u9fa5]   //匹配中文字符的正则表达式：
//(.*)(\.jpg|\.bmp)$   //只能是jpg和bmp格式
//^\d{4}\-\d{1,2}-\d{1,2}$  //只能是2004-10-22格式
//^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$　 //匹配email地址
//^[a-zA-z]+://匹配(\w+(-\w+)*)(\.(\w+(-\w+)*))*(\?\S*)?$　　//匹配url
//(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$ //电话

///  ^[A-Za-z][A-Za-z0-9_\\-]{3,15}$/

/**
 * @author shiwei 2013-4-1 <br/>
 * 演示正则表达式的使用。
 * 		①：匹配:regex1()
 * 				其实使用的就是String类中的matches方法。
 * 		②：切割:regex2()
 * 				其实使用的就是String类中的split方法。 
 * 		③：替换:regex3();
 * 				其实使用的就是String类中的replaceAll()方法。
 * 		④：获取:regex4()
 * 				获取符合条件的字符串：只能使用匹配器：Matcher
 * 
 * 
 * jdk参考文档中，可以通过String.split(String regex)方法，找到正则表达式的链接。
 * 正则表达式基本语法：
 *  
 * 
 */
public class TestRegex {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		regex4();
	}
	
	/**
	 * 匹配：使用String类中的matches方法。
	 */
	public static void regex1(){
		
		//需求一：验证qq号：4-15为数字，首位不能为0
		String str = "123456456";
		String regex = "[1-9][0-9]{3,14}";
//		regex = "[1-9]\\d{3,14}"; // 效果是一样的，使用\\d
		boolean qf = str.matches(regex);
		System.out.println("qq:"+str+",验证结果："+qf);
		
		//
		str = "aoob";
//		regex = "ao?b"; //aoob--> 不匹配。? :0个或1个
//		regex = "ao*b"; //aoob--> 匹配。*：0个或多个
//		regex = "ao+b";   //aoob--> 匹配。 +：1个或多个
		regex = "ao{2,}b";//aoob--> 匹配。{2,}:大于等于2个
		
		boolean sb = str.matches(regex);
		System.out.println(str+":"+sb);
	}

	/**
	 * 演示切割：使用spring.split(regex);
	 */
	public static void regex2(){
		
		
		String str = null;
		String regex = null;
		String [] strs = null;
		
		//姓名之间，有多个空格。[不固定个数]
//		str = "xiaoqiang   liming         zhangsan";
//		regex = " +";
		
		//在正则表达式中，点号是特殊符号，需要转义：\\.
//		str = "xiaoqiang.liming.zhangsan";
//		regex = "\\.";
		
		//分隔字符为叠词，如示例为ttt 和 mmmmm。
		str = "zhuangguangttlimingmmmmmzhangsan";
		//在正则表达式中，() 封装的是组，这里第二位的字符，用到了第一位的字符，复用的时候，就是用()。而且，会自动给组添加编号，第一个组为1，依次类推。
		//下列表达式的含义是：任意字符，至少被复用一次。如： tt , mmm 符合。
		// . 代表任意字符， (.) 代表这组 ； \\1 代表引用第一组；--> 任意字符，出现两次。
		regex = "(.)\\1+";
		
		strs = str.split(regex);
		print(strs);
		
	}
	
	/**
	 * 替换：使用String的replaceAll方法。
	 */
	public static void regex3(){
		
		String str = null;
		String retStr = null;
		
		
		//需求一：将字符串中的叠词，替换成 #
		str = "xiaoqiangttlimingmmmmmzhangsan";
		retStr = str.replaceAll("(.)\\1+", "#");
		
		//需求二：将字符串中的多个叠词，替换成一个。如：tt --> t  mmmmm--> m
		//TODO 一个方法里，第一个参数使用了正则表达式，第二个参数中如果想引用第一个参数匹配的结果[如第一次为tt符合，则匹配的字符就是t，因为使用的是replaceAll，所以会多次引用]，使用$ ,$1代表引用第一组的结果。
		retStr = str.replaceAll("(.)\\1+", "$1");
		
		
		//需求三：隐藏手机的中间4位。
		str = "15800001111";
		//获取第一组的前三位，与后四位。中间四位，替换成*
		//用括号进行分组。(\\d{3}) 为第一组 ； (\\d{4}) 为第二组。$1****$2 代表引用第一组，第二组的结果。
		retStr = str.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
		
		
		System.out.println(str+"---->"+retStr);
	}
	
	/**
	 * 获取：(典型操作)
	 * 		①：将正则规则进行对象的封装。 
	 * 			Pattern p = Pattern.compile("a*b");
	 * 		②：通过正则对象的matcher方法字符串相关联。获取要对字符串操作的匹配器对象Matcher .
 	 * 			Matcher m = p.matcher("aaaaab");
 	 * 		③：通过Matcher匹配器对象的方法对字符串进行操作。
 	 * 			boolean b = m.matches();
	 * 如果只是用一次，简洁方式： boolean b = Pattern.matches("a*b", "aaaaab");
	 * 
	 */
	public static void regex4(){
		String str = null;
		String regex = null;
		
		str = "da jia hao,ming tian bu fang jia!";
		//其中：\b 代表的是单词边界 ，如这里的逗号，感叹号，空格，都是。
		regex = "\\b[a-z]{3}\\b"; 
		Pattern p = Pattern.compile(regex);
		Matcher matcher = p.matcher(str);
		//matcher.find()：尝试查找与该模式匹配的输入序列的下一个子序列。找到返回true，否则返回false
		//matcher.group()：返回由以前匹配操作所匹配的输入子序列。如上面的 jia 。注意：调用matcher.group()之前，必须先查找一次。
		//matcher.start(), matcher.end(): 返回匹配的字符串的起始位置： [start,end)
		while(matcher.find()){
			System.out.println(matcher.group());
			System.out.println(matcher.start()+":"+matcher.end());
		}
		
		
	}
	/**
	 * 打印
	 * @param strs
	 */
	public static void print(String[] strs){
		for(String str:strs){
			System.out.println(str);
		}
	}
}
