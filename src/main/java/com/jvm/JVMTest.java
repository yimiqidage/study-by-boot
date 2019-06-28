package com.jvm;

/**
 * @author weishi8
 * @create 2019-04-28
 * @description
 */
public class JVMTest {



    public static void main(String[] args) {
        int i1 = 9;
        int i2 = 9;
        int i3 = 9;

        final int int1 = 9;
        final int int2 = 9;
        final int int3 = 9;

        System.out.println(i1==int1);
        System.out.println(i1==int3);
        System.out.println(int1==int2);
        System.out.println(int1==int3);

        System.gc();


        Integer integer1 = 9;
        Integer integer2 = 9;
        Integer integer3 = 9;

        System.out.println(int1==integer1);
        System.out.println(integer1==integer3);
    }
}
