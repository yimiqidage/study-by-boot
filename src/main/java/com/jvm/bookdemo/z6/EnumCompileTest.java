package com.jvm.bookdemo.z6;

/**
 * 测试枚举类的编译代码
 * @author weishi8
 * @create 2019-05-07
 * @description
 */
public enum EnumCompileTest {
    MAN(1),
    MAIL(2),
    FEMAIL(3);

    private int value;

    //这个就是枚举的构造函数，跟一般的class的构造函数是一样的。
    private EnumCompileTest(int value){
        this.value=value;
    }
    //同样可以给枚举设置方法。
    public int getValue(){
        return this.value;
    }
}
