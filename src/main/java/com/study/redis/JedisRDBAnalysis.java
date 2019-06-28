package com.study.redis;

import redis.clients.jedis.Jedis;

/**
 * @author weishi8
 * @create 2019-04-10
 * @description jedis的rdb文件解析，通过此方式，将dump.rdb文件，输出为一个文本文件，直接查看redis中所有数据的值；
 * 使用此工具前提：
 * 1、需要本机安装redis；
 * 2、执行此功能时，会清空本机redis中的所有数据，请慎重；
 * 转换后数据查看：
 * 1、如果为列表、集合、有序集合，转换后格式为[a,b,c]
 * 2、如果为hash，转换后为json格式
 * 3、如果为字符串，转换后为
 */
public class JedisRDBAnalysis {

    /**
     * 文件处理完毕后，输出后的文件格式
     */
    public static final String OUT_FILE_PATH = "";

    public static Jedis jedis = null;

    public static void initJedis(){
        jedis = new Jedis("127.0.0.1",6379);
    }

    public static void loadRDBFile(){

    }

    public static String translateList(String key){
        return null;
    }

    public static String translateSet(String key){
        return null;
    }

    public static String translateZset(String key){
        return null;
    }

    public static String translateHash(String key){
        return null;
    }

    public static String translateString(String key){
        return null;
    }
    public static void main(String[] args) {

    }

}
