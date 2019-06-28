package com.model;

/**
 * 单例模式：双重检验锁
 * @author weishi8
 * @create 2019-05-27
 * @description
 */
public class SingletonDoubleCheck {
    private static SingletonDoubleCheck instance;
    private SingletonDoubleCheck(){};
    public static SingletonDoubleCheck getInstance (){
        if(instance==null){
            synchronized (SingletonDoubleCheck.class){
                if(instance==null){
                    //这里可能出现问题：instance = new SingletonDoubleCheck()并不是原子操作，JVM大概通过三个步骤进行初始化：
                    //1、给 instance 分配内存
                    //2、调用 Singleton 的构造函数来初始化成员变量
                    //3、将instance对象指向分配的内存空间（执行完这步 instance 就为非 null 了）
                    //可能出现的问题：如果指令重排序的结果是：1-3-2，那么在线程A执行完步骤3之后，线程B就来获取instance，就会直接返回，但此时还未进行初始化，导致线程B抛出异常。
                    instance = new SingletonDoubleCheck();
                }
            }
        }
        return instance;
    }
}
