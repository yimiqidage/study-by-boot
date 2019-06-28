package com.model;

/**
 * 添加volatile关键字的双重检验锁
 * @author weishi8
 * @create 2019-05-27
 * @description
 */
public class SingleDoubleCheckVolatile {
    //通过给instance添加volatile关键字，来禁止指令重排序优化，从而避免之前的问题
    private static volatile  SingleDoubleCheckVolatile instance;
    private SingleDoubleCheckVolatile(){};
    public static SingleDoubleCheckVolatile getInstance (){
        if(instance==null){
            synchronized (SingleDoubleCheckVolatile.class){
                if(instance==null){
                    instance = new SingleDoubleCheckVolatile();
                }
            }
        }
        return instance;
    }
}
