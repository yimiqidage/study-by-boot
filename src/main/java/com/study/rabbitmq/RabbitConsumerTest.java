package com.study.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author weishi8
 * @create 2019-04-23
 * @description 消费者示例
 */
public class RabbitConsumerTest {
    /**
     * 队列名称
     */
    private static final String QUEUE_NAME="queue_demo";
    /**
     * rabbitMQ服务器的IP
     */
    public static final String SERVER_IP="10.100.137.16";
    /**
     * rabbitMQ服务器的端口
     */
    public static final int SERVER_PORT= 5672;
    public static void main(String[] args) throws  Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setPort(SERVER_PORT);
        factory.setHost(SERVER_IP);
        factory.setUsername("root");
        factory.setPassword("root");
        Connection connection =  factory.newConnection();
        final Channel channel = connection.createChannel();
        //信道上，消费者保持的最大未确认消息的数量
        channel.basicQos(64);
        //可以通过无限循环的方式，一直监听队列，有数据就立马消费。
        while (true) {
            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    System.out.println("接收到消息：" + new String(body));
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            };
            channel.basicConsume(QUEUE_NAME,consumer);
            TimeUnit.SECONDS.sleep(5);
        }


//        channel.close();
//        connection.close();

    }
}

