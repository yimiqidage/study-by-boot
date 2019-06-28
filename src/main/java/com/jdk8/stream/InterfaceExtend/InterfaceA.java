package com.jdk8.stream.InterfaceExtend;

public interface InterfaceA {
    default void hello(){
        System.out.println("hello from A");
    }
}
