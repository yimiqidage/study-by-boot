package com.test.map;

import java.util.HashMap;
import java.util.Map;


public class TestMap {

    /**
     * 测试map的clear方法，调用clear方法后，会清空map数据，但是不会变成null
     * @title map
     */
    public static void map(){
        Map<String,String>map = new HashMap<String, String>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        System.out.println(map.toString());
        map.clear();
        System.out.println(map.toString());
        map.put("key4", "value4");
        System.out.println(map.toString());
    }
    
    public static void main(String[] args) {
        map();
    }
}
