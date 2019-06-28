package com.jvm.stacks;

/**
 * @author weishi8
 * @create 2019-05-14
 * @description 栈深度测试类
 */
public class StackDeepTest {
    public static void main(String[] args) {
        int [] a = new int [10000];
        a[22] = 2;
        a[23] = 4;
        a[24] = 4;
        a[25] = 4;
        StackDeep sd = new StackDeep();
        sd.add();

        Object obj = new StackDeep();
    }
}

class StackDeep{
    public static int count = 0;
    public void add (){
        count++;
        try{
            add();
        }catch (Error e) { //这里需要捕获Error或者 Throwable ，因为抛出的是StackOverflowError
//            e.printStackTrace();
            System.out.println("栈深度："+count);
        }

    }
}