package com.jdk8.stream;

import java.io.File;
import java.io.FileFilter;

/**
 * @author weishi8
 * @create 2019-06-13
 * @description 简单演示，函数式编程。通过 File::isHidden 代表的是调用File的对象的isHidden方法
 */
public class StreamDemo {

    public static void main(String[] args) {

     // jdk8之前的代码流程：
     File[] hiddenFiles = new File("/Users/weishi8/study").listFiles(new FileFilter() {
         @Override
         public boolean accept(File file) {
             return file.isHidden();
         }
     });

     System.out.println(printFile(hiddenFiles));

     //jdk8 可以实现的代码流程
     File[] hiddenFiles2 = new File("/Users/weishi8/study").listFiles(File::isHidden);

     System.out.println(printFile(hiddenFiles2));
    }

    public static String printFile(File[] files){
        StringBuilder sb = new StringBuilder();
        for(File file:files){
            sb.append(file.getName()).append(",");
        }
        return sb.toString();
    }
}

