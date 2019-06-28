package com.jvm.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author weishi8
 * @create 2019-05-13
 * @description 泛型测试，在java源文件中，有泛型，编译后，泛型就被擦除了。
 */
public class GenericsTest {

    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer();
        if(args!=null && args.length>0){
            for (int i = 0; i < args.length ; i++) {
                sb.append(args[i]).append(",");
            }
        }

        System.out.println("main 方法接收参数："+sb.toString());
        Map<String,String> map  = new HashMap<String,String>();
        map.put("key1","value1");
        System.out.println(map);

        System.out.println(Integer.parseInt("6c69",16));
    }

//    public static String test(List<String> list){
//        return "a";
//    }
    public static int test(List<Integer>listb){
        return 1;
    }
}
