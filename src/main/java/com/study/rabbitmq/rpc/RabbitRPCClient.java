package com.study.rabbitmq.rpc;

import com.rabbitmq.client.*;
import com.study.rabbitmq.RabbitConsumerTest;

/**
 * 参照【P71】 4.6RPC实现
 * @author weishi8
 * @create 2019-04-24
 * @description
 */
public class RabbitRPCClient {
    private String replyQueueName="";

    RabbitRPCClient() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setPort(RabbitConsumerTest.SERVER_PORT);
        factory.setHost(RabbitConsumerTest.SERVER_IP);
        factory.setUsername("root");
        factory.setPassword("root");
        Connection connection =  factory.newConnection();
        final Channel channel = connection.createChannel();
        replyQueueName = channel.queueDeclare().getQueue();
        Consumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(replyQueueName,true,consumer);
    }
    public static void main(String[] args) throws Exception {

    }
}
