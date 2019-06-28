package com.jvm.bookdemo.z6;


import java.util.ArrayList;
import java.util.List;

/**
 * 查看class编译后的文件，源码示例
 * @author weishi8
 * @create 2019-05-07
 * @description
 */
public class ClassCompileTest  {
    public static final String FINAL_CONSTANT_PARAM = "111";
    public static String CONSTANT_PARAM = "222";
    public static int INT_PARAM = 12*8;

    public String appendStr(String paraStr,String prefix){

        Long g = Long.MAX_VALUE;
        System.out.println(g);

        int a = 1;
        int b = 2*6;
        int c = 3;
        int d = 4;
        int e = 6;
        int f = a+b*3+c+d+e;
        System.out.println(f);

        //泛型，用于查看LocalVariableTypeTable
        List<ClassCompileTest>list = new ArrayList<ClassCompileTest>();
        ClassCompileTest ct = new ClassCompileTest();
        list.add(ct);
        StringBuffer sb = new StringBuffer(paraStr);
        sb.append(prefix);
        return sb.toString();
    }


    public static void main(String[] args) {

        System.out.println(Integer.parseInt("4E",16));
        System.out.println(Integer.toHexString(0x0001|0x0020));
        System.out.println(Integer.toHexString(0x0001|0x4000));
    }

}
