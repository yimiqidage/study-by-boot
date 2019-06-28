package com.jvm.bookdemo.z6;

import java.util.Random;

public class TestClass {

    private int m;

    public int inc() {
        return m + 1;
    }

    public static int inc_s(){
        return new Random().nextInt(100);
    }

    public static int inc_s(int m){
        return m++;
    }

    public int inc2(int a,int b,int c ,int d ,int e,int f ,int g,int h ,int i){
        return a+b+c+d+e+f+g+h+i;
    }
    public static void main(String[] args) {

    }
}


