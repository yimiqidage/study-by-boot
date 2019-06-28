package com.test.hashcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shiwei 2013-3-15 <br/>
 * 测试在hashmap存储数据以后，如果修改了hashCode方法，使得原key值取得的hashcode值变化以后，是否能继续找到原来的value：
 * 	测试结果：不能。hashcode值修改以后，map就无法找到对应的value。
 *  测试步骤：在存储的时候，hashcode方法，始终返回1；在输出之前，修改hashcode方法，始终返回2.
 * 
 */
public class TestHashCode {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new TestHashCode().testHash();

	}

	public void  testHash(){
		Map<Hash, String>hm = new HashMap<Hash, String>();
		hm.put(new Hash("zhang1",11), "hashcode:1,name:zhang1,age:11");
		hm.put(new Hash("zhang2",12), "hashcode:1,name:zhang2,age:12");
		
		System.out.println(hm.get(new Hash("zhang1",11)));
		System.out.println(hm.get(new Hash("zhang2",12)));
		
		System.out.println("---------------------------");
		
		System.out.println(hm.get(new Hash("zhang1",11)));
		System.out.println(hm.get(new Hash("zhang2",12)));
	}
	
	public class Hash {
		private String name;
		private int age;
		public Hash(String name, int age) {
			this.name = name;
			this.age = age;
		}
		
		
		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public int getAge() {
			return age;
		}


		public void setAge(int age) {
			this.age = age;
		}


		public int hashCode(){
			
//			return 1;
			return 2;
		}
		
		public boolean equals(Object obj){
			Hash hash = (Hash)obj;
			return (this.getAge()==hash.getAge() && this.getName().equals(hash.getName()));
		}
	}
}
