package com.study.redis;

import redis.clients.util.JedisClusterCRC16;

/**
 * @author weishi8
 * @create 2019-04-19
 * @description jedis中可以用的工具类
 */
public class JedisUtil {

    /**
     * jedis提供的可以计算集群中，某个key值的槽点
     * @param key
     * @return
     */
    public static int getSlot(String key){
        System.out.println("1-计算槽点："+key);
        int slot =  JedisClusterCRC16.getSlot(key);
        System.out.println("2-key值："+key+",对应计算结果："+slot);
        return slot;
    }

    public static void main(String[] args) {
        getSlot("msg");
    }
}
