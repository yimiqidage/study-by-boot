package com.jvm.bookdemo.z4;

import java.util.ArrayList;
import java.util.List;

/**
 * @author weishi8
 * @create 2019-04-30
 * @description
 */
public class ClassesTest {
    private int m;
    public int inc() {
        return m + 1;
    }

    public static void main(String[] args) throws InterruptedException {
        List<Object>list = new ArrayList<Object>();
        for (int i = 0; i < 100; i++) {
            Thread.sleep(1000);
            list.add(new Object());
            System.out.println("list.size:"+list.size());
        }
        System.out.println("test class");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
