package com.test.regex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author shiwei 2013-4-1 <br/>
 * 示例网路爬虫：
 * 从互联网的页面中，找到邮件地址，并且存储。
 * 
 */
public class TestReptile {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		List<String> list = getMail();
		for(String m:list){
			System.out.println(m);
		}
	}

	
	public static List<String>getMail() throws IOException{
		
		List< String > list = new ArrayList<String>();
		
		URL url = new URL("http://zhidao.baidu.com/question/537176988.html?push=keyword");
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
		
		String line = null;
		Pattern p = Pattern.compile("\\w+@\\w+(\\.\\w+)+");
		while((line=br.readLine())!=null){
			
			Matcher matcher = p.matcher(line);
			while(matcher.find()){
				list.add(matcher.group());
			}
		}
		
	
		return list;
		
	}
}
