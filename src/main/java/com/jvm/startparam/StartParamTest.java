package com.jvm.startparam;

/**
 * 测试jvm启动参数
 * @author weishi8
 * @create 2019-04-29
 * @description
 */
public class StartParamTest {

    /**
     * VM参数：-Dtest.name="zhang san"
     * @param args
     */
    public static void main(String[] args) {
        //通过启动参数设置-Dtest.name 来传值
        System.out.println(System.getProperty("test.name"));
    }
}
