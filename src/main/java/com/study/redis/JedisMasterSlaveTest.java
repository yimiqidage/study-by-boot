package com.study.redis;

import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author weishi8
 * @create 2019-04-18
 * @description redis主从结构
 */
public class JedisMasterSlaveTest {

    private Jedis jedisClientMaster = null;
    private Jedis jedisClientSlave = null;

    public JedisMasterSlaveTest(){
        //1、单点的redis，通过Jedis对象来设置，直接指定IP，端口即可
        //1、设置redis的master
        this.jedisClientMaster = new Jedis("10.100.137.16",6390);
        //2、设置redis的slave
        this.jedisClientSlave = new Jedis("10.100.137.16",6391);
    }

    public static void main(String[] args) {
        JedisMasterSlaveTest jedis = new JedisMasterSlaveTest();
        SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(new Date());
        jedis.jedisClientMaster.set("msg","from JedisMasterSlaveTest.java,java set jedis key,"+dateStr);
        String result = jedis.jedisClientMaster.get("msg");
        System.out.println("获取msg值："+result);
    }

}
