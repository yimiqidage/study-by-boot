package com.jdk8.stream.codeStyle;

import org.apache.commons.lang.StringUtils;

import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.temporal.TemporalUnit;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiPredicate;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 编程技巧演示
 */
public class CodeStyle {

    public static void main(String[] args) throws  Exception{
        Function<String,Integer> function = Integer::parseInt;
        DoubleUnaryOperator convertCtoF = curriedConverter(9.0/5, 32);
        convertCtoF.applyAsDouble(100);

        List<? extends Number> numbers = new ArrayList<Integer>();

        //Collections.EMPTY_LIST初始化的对象，不能直接调用add方法
        List<Integer>list = Collections.EMPTY_LIST;
        List<Integer>list2 = new ArrayList<>();
        list2.add(1);
//        list.add(2);
        System.out.println(StringUtils.join(list,","));
        AtomicInteger atomicInteger = new AtomicInteger(100);
        int min = atomicInteger.accumulateAndGet(10, Integer::min);
        System.out.println(min);


        List<String>lines = Files.lines(Paths.get("/Users/weishi8/Desktop/00/testFiles.txt"))
                .skip(2)
                .limit(2)
                .collect(Collectors.toList());
        System.out.println(StringUtils.join(lines,","));

        BiPredicate<Path, BasicFileAttributes> matcher = (path,attr)->{
            if(path.endsWith("00") && attr.lastModifiedTime().compareTo(FileTime.from(Instant.now()))< 0){
                return true;
            }
            return false ;
        };
        Stream<Path>pathStream = Files.find(Paths.get("/Users/weishi8/Desktop/00"),3,matcher,FileVisitOption.FOLLOW_LINKS);
        System.out.println(StringUtils.join(pathStream.collect(Collectors.toList()),","));


        OptionalInt sum = IntStream.rangeClosed(1,20).reduce((a, b)->a+b);
        System.out.println(sum.orElse(0));

        List<String>list1 = Arrays.asList("a","b","c");
        List<String>list3 = new ArrayList<>();
        list1.forEach(s->list3.add(s+"_1"));
        System.out.println(StringUtils.join(list3,","));

    }

    static void testFinal(final int a){

    }

    static double converter(double x, double f, double b) {
        return x * f + b;
    }

    static DoubleUnaryOperator curriedConverter(double f, double b){
        return (double x) -> x * f + b;
    }

}
