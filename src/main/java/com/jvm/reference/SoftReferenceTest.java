package com.jvm.reference;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * 软引用适合做缓存。
 * 内存不足时，会回收软引用的内存。
 */
public class SoftReferenceTest {

    public static void main(String[] args) throws InterruptedException {

        SoftReference<byte[]> sk = new SoftReference<>(new byte[1024*1024*10]);
        System.out.println(sk.get());

        System.gc();

        Thread.sleep(500);
        System.out.println(sk.get());

        byte[]t = new byte[1024*1024*12];

        System.out.println(sk.get());

    }
}
