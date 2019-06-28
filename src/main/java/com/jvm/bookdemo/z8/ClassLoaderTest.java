package com.jvm.bookdemo.z8;

/**ClassLoaderTest
 * @author weishi8
 * @create 2019-05-13
 * @description
 */
public class ClassLoaderTest {
    public static void main(String[] args) {
        System.out.println(System.getProperty("java.ext.dirs"));
        ClassLoader c = ClassLoaderTest.class.getClassLoader();//获取ClassLoaderTest的加载器
        System.out.println(c.toString());
        ClassLoader c1 = c.getParent();  //获取c这个类加载器的父类加载器
        System.out.println(c1);
        ClassLoader c2 = c1.getParent();//获取c1这个类加载器的父类加载器
        System.out.println(c2);
    }
}
