package com.study.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author weishi8
 * @create 2019-04-08
 * @description 设置集群redis客户端
 * 相关注意点：
 * 1.连接的几个节点中，任意一个节点，能够连接集群，均可以正常使用。
 * 2.集群列表Set<HostAndPort>jedisClusterNode中，只要有一个可用，就不会报错。之所以连接多个节点，是为了防止某些节点宕机，导致无法连接到集群。
 * 3.即使连接的是cluster中的slave，也可以进行集群操作。
 */
public class JedisClusterTest2 {

    //redis集群列表
    private Set<HostAndPort> jedisClusterNode = null;
    //超时时间
    private static final int timeout = 300;
    //最大
    private static final int maxRedirections = 30;

    private JedisCluster jedisCluster ;

    public JedisClusterTest2(){
        jedisClusterNode = new HashSet<HostAndPort>();

        //1、断掉其中一个节点，如6379，客户端仍然可以继续使用。
        //2、只要其中一个节点可用，就可以。
        //3、也可以配置slave的节点，依然可用。
        //more：如果有多个节点，继续调用add方法添加
        jedisClusterNode.add(new HostAndPort("10.100.137.16",6380));
        jedisClusterNode.add(new HostAndPort("10.100.137.16",6382));
        jedisClusterNode.add(new HostAndPort("10.100.137.16",6384));

        //设置连接池
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        //单位都是秒
        poolConfig.setMaxIdle(30);
        poolConfig.setMaxTotal(30);
        poolConfig.setMinIdle(2);
        //初始化JedisCluster对象，也可以使用其他构造方法
        this.jedisCluster = new JedisCluster(jedisClusterNode,timeout,maxRedirections,poolConfig);
    }

    public static void main(String[] args) throws InterruptedException {
        JedisClusterTest2 jedis = new JedisClusterTest2();
        SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(new Date());
        jedis.jedisCluster.set("msg","java set jedis key,"+dateStr);
        System.out.println("jedisCluster 获取返回值："+jedis.jedisCluster.get("msg"));

        while(true){
            Thread.sleep(1000);;
            jedis.jedisCluster.set("msg","java set jedis key,"+dateStr);
        }


    }
}
