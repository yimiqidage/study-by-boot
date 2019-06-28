package com.jdk8.stream.InterfaceExtend;

public class ClassD extends ClassC implements InterfaceA, InterfaceB {


    /**
     * 测试调用优先级：
     * 1、ClassD继承ClassC，ClassC实现了InterfaceA接口，但未重写 InterfaceA 的方法；
     * 2、InterfaceA 继承 InterfaceB 接口
     * 3、输出结果：hello from B
     * 原因：优先选择拥有最具体实现的默认方法的接口，即如果B继承了A，那么B就比A更加具体。
     * @param args
     */
    public static void main(String[] args) {
        new ClassD().hello();
    }
}
