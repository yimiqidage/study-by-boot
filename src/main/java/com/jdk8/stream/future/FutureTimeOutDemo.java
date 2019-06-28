package com.jdk8.stream.future;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 演示任务执行超时情况下，任务执行情况：
 * 1、任务通过ExecutorCompletionService.submit方法，进行提交；
 * 2、方法提交后，通过Future对象获取执行结果，获取结果超时；
 * 3、任务会继续执行，直到执行完毕；但此期间线程池不会执行下一个任务，因为线程池无空闲线程。
 */
public class FutureTimeOutDemo {

   public static Log log = LogFactory.getLog(FutureTimeOutDemo.class);
    public static final int SIZE = 10;

    public static void main(String[] args) {
        testCompletionService();
    }
    /**
     * 演示ExecutorCompletionService用法
     */
    public static void testCompletionService(){
        ExecutorService executor = null;
        ExecutorCompletionService<Double> completionService = null;
        try{
            //1、创建线程池大小为2
            executor = Executors.newFixedThreadPool(2);
            //2、创建ExecutorCompletionService对象
            completionService = new ExecutorCompletionService<Double>(executor);
            List<Future<Double>> listFuture = new ArrayList<Future<Double>>(10);
            for (int i = 0; i < SIZE; i++) {
                //3、获取任务
                MyCallableTimeOut callable = new MyCallableTimeOut(String.valueOf(i));
                //4、提交任务，获取返回Future
                Future<Double>future = completionService.submit(callable);
                listFuture.add(future);
            }

            for (int i = 0; i <SIZE ; i++) {
                Double price = null;
                try {
                    // 5、建议使用get( timeout, unit) 方法；如果不设置超时时间，可能一直阻塞
                    price = listFuture.get(i).get(1, TimeUnit.SECONDS);
                } catch (TimeoutException e) {
                    log.info("超时..."+i);
                }
                log.info("completionService take get :"+price);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //6、使用完，要关闭线程池
            if(executor !=null){
                executor.shutdown();
            }
        }
    }
}


/**
 * Callable 接口实现类，做具体的业务逻辑
 * 1、接口实现类（相当于Thread），设置作用于为prototype，防止出现线程问题；
 */
@Scope("prototype")
class MyCallableTimeOut implements Callable<Double> {
    public static final Log log = LogFactory.getLog(MyCallableTimeOut.class);
    private String name;

    @Override
    public Double call() throws Exception {
        log.info("callable[" + name + "]执行call方法...");
        Thread.sleep(10000);
        return new Random().nextDouble() * 10;
    }

    MyCallableTimeOut(String name) {
        this.name = name;
    }
}