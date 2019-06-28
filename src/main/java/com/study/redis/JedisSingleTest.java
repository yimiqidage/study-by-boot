package com.study.redis;

import redis.clients.jedis.Jedis;

/**
 * @author weishi8
 * @create 2019-04-08
 * @description 设置单点redis客户端，集群请参照JedisClusterTest
 */
public class JedisSingleTest {

    private Jedis jedisClient = null;

    public JedisSingleTest(){
        //1、单点的redis，通过Jedis对象来设置，直接指定IP，端口即可
        this.jedisClient = new Jedis("127.0.0.1",6379);
    }

    public static void main(String[] args) {
        JedisSingleTest jedis = new JedisSingleTest();
        jedis.jedisClient.set("msg","java set jedis key");
    }

}
