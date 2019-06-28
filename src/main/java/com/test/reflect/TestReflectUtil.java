package com.test.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import com.test.bean.Person;


/**
 * 演示反射的使用：
 * 	通过反射找到方法名，参数名等
 * 
 */
public class TestReflectUtil {

	
	/**
	 * 根据指定的类，将List集合转换成json格式进行输出。
	 * 		注：只能处理基本类型，以及ByteEnum的实现类，java.util.Date
	 * 
	 * @param className 全路径类名
	 * @param col list集合
	 * @param prefix 产生的json传的前缀，默认为 "list"
	 * @return json格式的字符串
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	@SuppressWarnings("unchecked")
	public static  <T> String listToJson(String className,List<T>col,String prefix) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		
		if(col==null || col.size()==0 || className==null || className.equals("") ) return "";
		
		if(prefix==null || prefix.equals("")) prefix = "\"list\"";
		
		StringBuilder sb = new StringBuilder("{"+prefix+":[");
		
		Class clazz = Class.forName(className);
		
		//使用LinkedHashMap保证输出的顺序一致
		LinkedHashMap<String,Method> methodMap = new LinkedHashMap<String, Method>();
		//性能优化：第一次获取所有的method以后，缓存到Map，提高效率。
		for(int k=0;k<col.size();k++){
			Object obj = col.get(k);
			if(k==0){
				Method[] methods = clazz.getMethods();
					for(int i=0;i<methods.length;i++){
						if(i==0) sb.append("{");
						Method method = methods[i];
						String methodName = method.getName();
						if(methodName.equals("getClass")) continue;
						
						if(methodName.startsWith("get")){
							String proName = methodName.substring(3,4).toLowerCase()+methodName.substring(4);
							String proValue = getObjValue(obj, method);
							
							sb.append("\""+proName+"\":\""+proValue+"\",");
							methodMap.put(proName, method);
						}
						
					}
			
			}else{
				for (String proName : methodMap.keySet()) {
				String proValue = getObjValue(obj, methodMap.get(proName));
				sb.append("\"" + proName + "\":\"" + proValue + "\",");
				}
				
			}
			
			String tempStr = sb.substring(0, sb.length()-1);
			sb = new StringBuilder(tempStr);
			sb.append("},{");
			
		}
		
		String tempStr = sb.substring(0, sb.length()-2);
		sb = new StringBuilder(tempStr).append("]}");
		return sb.toString();
	}

	/**
	 * 获得obj对象，调用method后，获得的方法值。 obj.method()
	 * @param obj
	 * @param method
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static String getObjValue(Object obj,Method method) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		
		String proValue = null; 
		Object tempValue = method.invoke(obj,new Object[]{});
		 if(tempValue instanceof Date){
			SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			proValue = formate.format((Date)tempValue);
		}else{
			proValue = tempValue.toString();
		}
		return proValue;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		List<Person>list = new ArrayList<Person>();
		Person p1 = new Person("张三", 20);
		Person p2 = new Person("李四", 22);
		Person p3 = new Person("王五", 24);
		
		for(int i=0;i<1;i++){
			list.add(p1);
			list.add(p2);
			list.add(p3);
		}
		long t1 = System.currentTimeMillis();
		System.out.println(listToJson("com.test.bean.Person", list, "list"));
		long t2 = System.currentTimeMillis();
		System.out.println("共耗时："+(t2-t1)+"毫秒");
	}

}
