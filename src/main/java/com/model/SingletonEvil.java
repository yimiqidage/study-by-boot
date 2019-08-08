package com.model;

/**
 * Creator weishi8
 * Date&Time 2019-08-08 15:40
 * description 单例模式：恶汉模式
 * 饿汉式在类加载过程中就对单例对象进行初始化创建对象，同时保证了线程安全。
 */
public class SingletonEvil {
    private SingletonEvil(){}
    private static final SingletonEvil singleton = new SingletonEvil();
    public static SingletonEvil getInstance(){
        return  singleton;
    }
}
