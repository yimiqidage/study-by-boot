package com.jdk8.stream.InterfaceExtend.simplesignature;

/**
 * 测试示例：
 * 1、一个类，同时实现了两个平级的接口，接口里有相同的方法；
 * 2、需要通过语法 X.super.m(...)，其中X是你希望调用的m方法所在的父接口
 */
class SimpleSignatureTest implements InterfaceAA,InterfaceBB{
    public static void main(String[] args) {
        new SimpleSignatureTest().hello();
    }

    @Override
    public void hello() {
        //显示说明，需要调用 InterfaceBB 的 hello 方法
        InterfaceBB.super.hello();
    }
}

public interface InterfaceAA {
    default void hello(){
        System.out.println("hello from InterfaceAA");
    }
}
interface InterfaceBB{
    default void hello(){
        System.out.println("hello from InterfaceBB");
    }
}

