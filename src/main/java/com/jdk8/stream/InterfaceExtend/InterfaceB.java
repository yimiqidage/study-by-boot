package com.jdk8.stream.InterfaceExtend;

public interface InterfaceB extends InterfaceA{
    default void hello(){
        System.out.println("hello from B");
    }
}
