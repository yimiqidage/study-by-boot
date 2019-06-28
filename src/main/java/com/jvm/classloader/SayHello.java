package com.jvm.classloader;

/**
 * @author weishi8
 * @create 2019-05-20
 * @description
 */
public class SayHello {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        MyClassLoader mcl = new MyClassLoader();
        Class<?> clazz = Class.forName("com.jvm.classloader.People", true, mcl);
        Object obj = clazz.newInstance();
        System.out.println(System.getProperty("java.class.path"));

        System.out.println(obj);
        System.out.println(obj.getClass().getClassLoader());//打印出我们的自定义类加载器
    }
}
