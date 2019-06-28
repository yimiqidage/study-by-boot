package com.jvm.bookdemo.z7;

/**
 * @author weishi8
 * @create 2019-05-10
 * @description
 */
public class ClassInitTest {

    static {
        i = 3;  //  给变量复制可以正常编译通过
//        System.out.print(i);  // 这句编译器会提示“非法向前引用”
    }

    public static int i = 1;
    public static void main(String[] args) {
        System.out.println("i="+i);
    }
}

