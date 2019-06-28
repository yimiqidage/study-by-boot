package com.stack;

/**
 * @author weishi8
 * @create 2019-04-24
 * @description
 */
public class StackTest {
    public static void main(String[] args) {
        int a = 3;
        int b = 3;
        a = 4;
        System.out.println("a="+a+",b="+b);

        stackOverFlowErrorTest();
    }

    /**
     * 测试栈内存溢出，抛出StackOverflowError异常
     */
    public static void stackOverFlowErrorTest(){
        while (true){
            stackOverFlowErrorTest();
        }
    }
}
