package com.test.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.test.bean.Student;


/**
 * @author shiwei 2013-3-12 <br/>
 * map_keySet(): 使用map.keyset() 示例。<br/>
 * map_entrySet():使用map.entrySet() 示例。<br/>
 * 关于两种方法性能上的区别：在小于10万数据的时候，基本上差不多。但大于10万时，map.entrySet()的性能比keySet() 好。<br/>
 * 
 */
public class TestHashMap {

	public static void main(String[] args)  throws Exception{
		
		complex();
	}
	
	public static void test() throws Exception{
		
		//测试比较性能上的差异
		Map<String,String>map = new HashMap<String,String>();
		int total = 1000000;
		for(int i=0;i<total;i++){
			map.put("key"+i, "value"+i);
		}
		long time1 = System.currentTimeMillis();
		map_keySet(map);
		long time2 = System.currentTimeMillis();
		System.out.println("map_keySet:时间为["+(time2-time1)+"]毫秒");
		map.clear();
		for(int i=0;i<total;i++){
			map.put("key"+i, "value"+i);
		}
		time2 = System.currentTimeMillis();
		map_entrySet(map);
		long time3 = System.currentTimeMillis();
		System.out.println("map_entrySet：时间为["+(time3-time2)+"]毫秒");
	}
	/**
	 * 使用keySet 方法，获得所有的key，然后循环迭代。
	 * keySet() 方法返回的Set对象的原因是：防止key值重复。因为Set具有不重复的特性。
	 * @throws Exception
	 */
	public static void map_keySet(Map<String,String>map) throws Exception {
		
		
		Set<String> set  = map.keySet();
		for(Iterator<String> it = set.iterator();it.hasNext();){
			String key = it.next();
			 map.get(key);
//			System.out.println("map_keySet:"+key+":"+map.get(key));
		}
		
	}
	
	/**
	 * 使用entrySet() 方法，返回Map.Entry 对象，然后直接得到Map.Entry中的getKey(),getValue().
	 * @throws Exception
	 */
	public static void map_entrySet(Map<String,String>map) throws Exception {
		
		
		Set<Map.Entry<String, String>> entrySet = map.entrySet();
		Iterator<Map.Entry<String, String>> itor = entrySet.iterator();
		while(itor.hasNext()){
			Map.Entry<String, String>kv = itor.next();
			 kv.getKey();
			 kv.getValue();
//			System.out.println("map_entrySet:"+kv.getKey()+","+kv.getValue());
		}
		
	}
	
	/**
	 * 如果key为复杂对象，则需要实现hashcode和equals方法，如，指定同一name，统一age为同一个对象。否则，会重复存储。
	 * 
	 */
	public static void complex(){
		Map<Student,String> map = new HashMap<Student, String>();
		map.put(new Student("zhang1",11), "北京");
		map.put(new Student("zhang2",12), "上海");
		map.put(new Student("zhang3",13), "重庆");
		map.put(new Student("zhang2",12), "杭州");
		
		Iterator<Student> it = map.keySet().iterator();
		while(it.hasNext()){
			Student st = it.next();
			System.out.println(st.toString()+":"+map.get(st));
		}
		
	}
}
