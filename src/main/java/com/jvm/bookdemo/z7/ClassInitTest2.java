package com.jvm.bookdemo.z7;

/**
 * @author weishi8
 * @create 2019-05-10
 * @description
 */
public class ClassInitTest2 {
    public static void main(String[] args) {
        System.out.println("Sub.B="+Sub.B);
    }
}

 class Parent {

    public static int A = 1;
    static {
         A = 2;
     }
}

 class Sub extends Parent {
    public static int B = A;
}




