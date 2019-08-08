package com.model;

/**
 * Creator weishi8
 * Date&Time 2019-08-08 14:22
 * description 单例模式实现：延迟初始化占位类模式
 * <p>1、通过占位类实现；
 * <p>2、内部类 DelayInitHolderClass 为占位类；
 * <p>3、这种方式，是基于JVM在类的初始化阶段，保证的线程安全性：
 * <p> &nbsp;&nbsp;&nbsp;&nbsp;3.1 将 SingletonPlaceHolder 的实例化操作放置到一个静态内部类中；
 * <p> &nbsp;&nbsp;&nbsp;&nbsp;3.2 只有在第一次调用 getInstance 方法时，JVM才会加载内部类 DelayInitHolderClass，同时初始化 SingletonPlaceHolder 类实例；
 * <p> &nbsp;&nbsp;&nbsp;&nbsp;3.3 因此，即使不采取任何同步策略，getInstance 也是线程安全的。
 * <p> 4、这种模式，实际上是提前初始化（饿汉式）和延迟初始化（懒汉式）的综合模式。
 */
public class SingletonPlaceHolder {

    /**
     * 1、私有化构造函数
     */
    private SingletonPlaceHolder(){}

    /**
     * 2、定义延迟初始化占位类
     */
    private static class DelayInitHolderClass{
        //在类加载时，进行变量 INSTANCE 的初始化，因为是类变量，（不是实例变量），因此没有线程安全问题
        private static final  SingletonPlaceHolder INSTANCE  = new SingletonPlaceHolder();
    }
    /**
     * 3、提供实例调用入口：只有调用 getInstance 方法时，才会进行内部类 DelayInitHolderClass 的初始化
     * @return
     */
    public static  SingletonPlaceHolder getInstance(){
        return DelayInitHolderClass.INSTANCE;
    }
}

