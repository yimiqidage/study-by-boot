package com.study.rabbitmq.rpc;

import com.rabbitmq.client.*;
import com.study.rabbitmq.RabbitConsumerTest;

import java.io.IOException;

/**
 * 用于测试rpc，服务端代码
 * @author weishi8
 * @create 2019-04-24
 * @description
 */
public class RabbitRPCServer {
    public static final String RPC_QUEUE_NAME="rpc_queue";

    public static void main(String[] args) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setPort(RabbitConsumerTest.SERVER_PORT);
        factory.setHost(RabbitConsumerTest.SERVER_IP);
        factory.setUsername("root");
        factory.setPassword("root");
        Connection connection =  factory.newConnection();
        final Channel channel = connection.createChannel();
        channel.queueDeclare(RPC_QUEUE_NAME,true,false,false,null);
        channel.basicQos(1);
        System.out.println("等待RPC请求");
        Consumer consumer =  new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                AMQP.BasicProperties replyProps = new AMQP.BasicProperties.Builder().correlationId(properties.getCorrelationId()).build();
                String respStr = "";
                try{
                    String msg = new String(body,"UTF-8");
                    int n = Integer.parseInt(msg);
                    System.out.println("fib ("+msg+")");
                    respStr += fib(n);
                }catch (RuntimeException e){
                    e.printStackTrace();
                }finally {
                    channel.basicPublish("",properties.getReplyTo(),replyProps,respStr.getBytes("UTF-8"));
                }
                super.handleDelivery(consumerTag, envelope, properties, body);
            }
        };
        channel.basicConsume(RPC_QUEUE_NAME,false,consumer);
    }

    public static int fib (int n){
        if(n==0) return 0;
        if(n==1) return 1;
        return fib(n-1) + fib(n);
    }
}
