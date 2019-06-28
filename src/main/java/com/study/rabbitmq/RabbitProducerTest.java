package com.study.rabbitmq;

import com.rabbitmq.client.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;


/**
 * @author weishi8
 * @create 2019-04-23
 * @description rabbitmq 生产者代码示例
 */
public class RabbitProducerTest {

    /**
     * 交换器名称
     */
    private static final String EXCHANGE_NAME="exchange_demo";
    /**
     * 路由名称
     */
    private static final String ROUTING_KEY="routing_demo";
    /**
     * 队列名称
     */
    private static final String QUEUE_NAME="queue_demo";
    /**
     * rabbitMQ服务器的IP
     */
    private static final String SERVER_IP="10.100.137.16";
    /**
     * rabbitMQ服务器的端口
     */
    private static final int SERVER_PORT= 5672;


    public static void main(String[] args) throws  Exception{
        //初始化工厂
        ConnectionFactory factory =  new ConnectionFactory();
        //设置服务器IP，端口等信息
        factory.setHost(SERVER_IP);
        factory.setPort(SERVER_PORT);
        //设置连接的用户名、密码
        factory.setUsername("root");
        factory.setPassword("root");
        //初始化链接
        Connection conn = factory.newConnection();
        //初始化信道 -channel
        Channel channel = conn.createChannel();
        //初始化交换器，类型为direct，持久化的、非自动删除的交换器
        channel.exchangeDeclare(EXCHANGE_NAME,"direct",true,false,null);
        //初始化队列，持久化、非排他的、非自动删除的队列；
        channel.queueDeclare(QUEUE_NAME,true,false,true,null);
        //将交换器与队列，通过路由key进行绑定
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,ROUTING_KEY);
        SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(new Date());

        for (int i = 0; i <5 ; i++) {
            String msg = "hello world,time-["+i+"]:"+dateStr;
            //向mq发送消息
            channel.basicPublish(EXCHANGE_NAME,ROUTING_KEY, MessageProperties.TEXT_PLAIN,msg.getBytes("UTF-8"));
            System.out.println("发送消息："+msg);
        }

        //关闭信道
        channel.close();
        //关闭链接（如果不关闭channel，直接关闭Connection也可以，Connection关闭的时候，channel也会自动关闭）
        //最终都会调用到AMQChannel.processShutdownSignal 方法
        conn.close();
    }
}
