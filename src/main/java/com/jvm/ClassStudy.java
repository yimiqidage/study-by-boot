package com.jvm;



import sun.tools.java.ClassFile;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author weishi8
 * @create 2019-05-07
 * @description 用来测试：子类不能重写父类的私有方法
 */
public class ClassStudy extends  ParentTest{

    @Override
    public void publicMethod(int b) {
        super.publicMethod(b);
    }
}

class ParentTest{
    private void privateMethod(int a){
        System.out.println("ParentTest.a = "+a);
    }

    public void publicMethod(int b){
        System.out.println("ParentTest.b = "+b);
    }
}
