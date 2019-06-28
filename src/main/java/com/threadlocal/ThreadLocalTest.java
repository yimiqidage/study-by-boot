package com.threadlocal;

import com.jvm.bookdemo.z12.Student;

import java.util.Random;

/**
 * @author weishi8
 * @create 2019-05-21
 * @description ThreadLocal整体用法：
 * 1、在多线程的类（如ThreadLocalTest类）中，创建一个ThreadLocal对象t，用来保存线程间需要隔离处理的对象Student。
 * 2、在ThreadLocalTest类中，创建一个获取要隔离访问的数据的方法getStudent()，在方法中判断，若ThreadLocal对象为null时候，应该new()一个隔离访问类型的对象，并强制转换为要应用的类型。
 * 3、在ThreadLocalTest类的run()方法中，通过getStudent()方法获取要操作的数据，这样可以保证每个线程对应一个数据对象，在任何时刻都操作的是这个对象。
 * 参照地址：https://blog.51cto.com/lavasoft/51926
 */
public class ThreadLocalTest implements Runnable{

    public static ThreadLocal t = new ThreadLocal();

    private Student s = new Student();

    public static void main(String[] args) {
        ThreadLocalTest t = new ThreadLocalTest();
        for (int i = 0; i <1 ; i++) {
            Thread t1 = new Thread(t,"A-"+i);
            Thread t2 = new Thread(t,"B-"+i);
            t1.start();
            t2.start();
        }

    }
    @Override
    public void run() {
        useThreadLocalStudent();
        unUse();
    }

    /**
     * 不使用ThreadLocal进行对象存储
     */
    public void unUse(){
        String tname = Thread.currentThread().getName();
        Random r = new Random();
        int age = r.nextInt(100);
        System.out.println("thread-unuse-"+tname+" age is "+age);
        s.setAge(age);
        s.setName(tname);
        try {
            Thread.sleep(500);
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("thread-unuse-"+tname+" age is "+s.getAge());
    }

    /**
     * 使用ThreadLocal进行存储
     */
    public void useThreadLocalStudent(){
        String tname = Thread.currentThread().getName();
        Random r = new Random();
        int age = r.nextInt(100);
        System.out.println("thread-"+tname+" age is "+age);
        // 通过getStudent()获取Student对象
        Student s = getStudent();
        s.setAge(age);
        s.setName(tname);
        try {
            Thread.sleep(500);
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("thread-"+tname+" age is "+s.getAge());
    }

    /**
     * 创建当前线程拥有的对象Student
     * @return
     */
    public Student getStudent(){
        Student s = (Student)t.get();
        if(s==null){
            s = new Student();
            t.set(s);
        }
        return  s;
    }
}
