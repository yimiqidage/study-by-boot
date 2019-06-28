package com.jdk8.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author weishi8
 * @create 2019-06-13
 * @description 并行处理、串行处理示例
 *
 */
public class ParallelStreamDemo {

    public static void main(String[] args) {

        List<Apple> apples = new ArrayList<>(10000);

        for (int i = 0; i < 10000 ; i++) {
            apples.add(new Apple("color"+i,i));
        }

        List<Apple> apples2 = new ArrayList<>(10000);

        for (int i = 0; i < 10000 ; i++) {
            apples2.add(new Apple("color2"+i,i));
        }

        long time1 = System.currentTimeMillis();

        // 1、顺序执行
        List<Apple> heavyApples2 =
                apples2.stream().filter((Apple a) -> a.getWeight() > 150)
                        .collect(Collectors.toList());

        long time2 = System.currentTimeMillis();

        // 2、并发执行
        List<Apple> heavyApples =
                apples.parallelStream().filter((Apple a) -> a.getWeight() > 150).collect(Collectors.toList());


        long time3 = System.currentTimeMillis();

        System.out.println("耗时："+(time2-time1)+","+(time3-time2));

    }
}
