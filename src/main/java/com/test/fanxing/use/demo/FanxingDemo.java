package com.test.fanxing.use.demo;

import com.test.bean.Student;
import com.test.bean.Teacher;
import com.test.util.ToolNoFanxing;
import com.test.util.ToolWithFanxing;

public class FanxingDemo {

	/**
	 * 使用泛型以后，只能操作某一种特定的对象，否则会编译错误。并且不用进行类型转换，如果类型不一致，会编译错误。
	 */
	public static void useWithFanxing(){
		
		ToolWithFanxing<Student> tool = new ToolWithFanxing<Student>();
		tool.setValue(new Student("张三",20));
		Student stu = tool.getValue();
		
		//TODO 注意：这里只能操作一种对象
//		tool.setValue(new Teacher());
//		 stu = tool.getValue();
		
	}
	
	/**
	 * 不使用泛型，只能强制转换类型，并且编译不报错，只能在运行的时候抛出异常。
	 */
	public static void useNoFanxing(){
		
		ToolNoFanxing tool = new ToolNoFanxing();
		tool.setObject(new Student());
		Student stu = (Student)tool.getObject();
		
		tool.setObject(new Teacher());
		 stu = (Student)tool.getObject();
		
		
	}
	public static void main(String[] args) {
		
	}
	
}
