package com.stack;

import java.util.ArrayList;
import java.util.List;

/**堆测试类
 * @author weishi8
 * @create 2019-04-24
 * @description 通过new创建的对象，如果同时被A，B两个指针引用，通过A修改对象后，B的结果也会发生变化。
 */
public class HeapTest {
    public static void main(String[] args) {
        Student s = new Student("zhangsan","middle");
        Student s2 = s;
        s.setName("zhangsan-2");
        System.out.println("s.getName:"+s.getName()+",s2.getName:"+s2.getName());
        outOfMemoryExceptionTest();
    }

    /**
     * 测试栈内存溢出，抛出StackOverflowError异常
     */
    public static void outOfMemoryExceptionTest(){
        List<Student>list = new ArrayList<Student>();
        while (true){
            list.add(new Student());
        }
    }
}
