package com.jdk8.stream.future;

import com.emails.SendEmail;
import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 演示CompletableFuture
 */
@SpringBootApplication
@SpringBootTest
public class CompletableFutureDemo {

    @Autowired
    private JavaMailSenderImpl mailSender;

    public  static Log log = LogFactory.getLog(CompletableFutureDemo.class);

    /**
     * 测试 CompletableFuture 以及 completableFuture.whenComplete
     * @throws Exception
     */
    @Test
    public void test() throws Exception{

        //1、创建一个异步任务，执行sleep
        CompletableFuture<Integer>completableFuture = CompletableFuture.supplyAsync(CompletableFutureDemo::sleep);
        System.out.println("CompletableFuture.supplyAsync 初始化");
        //2、当异步任务执行完毕后，调用 whenCompleteTest 方法
        CompletableFuture<Integer> resultFuture = completableFuture.whenComplete((i,t)-> {
            try{
                whenCompleteTest(i, t);
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        System.out.println("CompletableFuture.whenComplete 初始化");
        try {
            //3-1、测试：如果没有调用 Future 的get方法，是否会执行 whenCompleteTest 方法 --> 不执行
            //3-2、 测试：如果调用 Future 的get方法，但是超时，看是否会执行 whenCompleteTest 方法 --> 不执行
            //3-3、测试：如果调用 Future 的get方法，并且等到返回，看是否会执行 whenCompleteTest 方法 --> 执行
            //结论：只有Future的方法，在正常返回时，才会调用注册的 whenCompleteTest 方法
            resultFuture.get(1, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("获取resultFuture结果");

//        while (true){
//            try {
//                Thread.sleep(1000);
//                System.out.println("休眠1秒...");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }

    public  void whenCompleteTest (Integer i,Throwable t) throws Exception{
        log.info("whenCompleteTest,completed:："+i);
        sendHtmlMail();
    };

    public static int sleep()  {
        int seconds= 5;
        log.info("sleep["+seconds+"]秒，开始");
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("sleep["+seconds+"]秒，结束");
        return new Random().nextInt(10);
    }

//    /**
//     *
//     */
//    public static void test1(){
//        Shop shop = new Shop("BestShop");
//        long start = System.nanoTime();
//        // 1、使用普通方法创建 CompletableFuture对象
//        Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
//        //2、使用工厂方法，创建 CompletableFuture对象
////        Future<Double> futurePrice = shop.getPriceBySupplyAsync("my favorite product");
//        long invocationTime = ((System.nanoTime() - start) / 1000000);
//        System.out.println("Invocation returned after " + invocationTime+ " msecs");
//        try {
//            double price = futurePrice.get();
//            System.out.printf("Price is %.2f%n", price);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        long retrievalTime = ((System.nanoTime() - start) / 1000000);
//        System.out.println("Price returned after " + retrievalTime + " msecs");
//    }


    /**
     * 发送包含HTML文本的邮件
     *
     * @throws Exception
     */
    @Test
    public void sendHtmlMail() throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo("563961284@qq.com");
        mimeMessageHelper.setFrom("shiwei8b402@126.com");
        mimeMessageHelper.setSubject("test 邮件测试【HTML】"+System.currentTimeMillis());

        StringBuilder sb = new StringBuilder();
        sb.append("<html><head></head>");
        sb.append("<body><h1>spring 邮件测试</h1><p>hello!this is spring mail test。</p></body>");
        sb.append("</html>");

        // 启用html
        mimeMessageHelper.setText(sb.toString(), true);
        // 发送邮件
        mailSender.send(mimeMessage);

        System.out.println("邮件已发送");

    }

}


