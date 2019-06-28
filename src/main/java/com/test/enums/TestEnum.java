package com.test.enums;

/**
 * 一般格式的枚举
 * @author s
 *
 */
//public enum TestEnum {
//	DOG,
//	CAT,
//	TIGER
//}

/**
 * 高级格式的枚举：
 * 构造函数中，带有参数的枚举
 */
public enum TestEnum {
	DOG(0),
	CAT(1),
	TIGER(2);

	private int value;
	
	//这个就是枚举的构造函数，跟一般的class的构造函数是一样的。
	private TestEnum(int value){
		this.value=value;
	}
	//同样可以给枚举设置方法。
	public int getValue(){
		return this.value;
	}
}
