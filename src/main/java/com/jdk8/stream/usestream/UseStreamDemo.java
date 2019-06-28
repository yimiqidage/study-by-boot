package com.jdk8.stream.usestream;

import org.apache.commons.lang.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class UseStreamDemo {

    public static List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
    public static void main(String[] args) {
//        OptionalInt optionalInt = IntStream.range(1,100).max();
//        System.out.println(optionalInt.orElse(0));

//        IntStream.rangeClosed(1,100)
//                .filter(b-> Math.sqrt(a*a +b*b)%1==0)

//        IntStream.rangeClosed(1,100).filter(
//               a-> IntStream.rangeClosed(a,100).filter(b->Math.sqrt(a*a + b*b)%1==0)
//                .mapToObj(b->new int[]{a,b,(int)Math.sqrt(a*a+b*b)})
//
//        );

        Stream.iterate(0,a->a+2).limit(10).forEach(System.out::println);
        Stream.generate(Math::random).limit(5).forEach(System.out::println);

//        useFlatMap();
//        test1();
//        retunAllNumbers();
//        Stream<int[]> pythagoreanTriples =
//                IntStream.rangeClosed(1, 100).boxed()
//                        .flatMap(a ->
//                                IntStream.rangeClosed(a, 100)
//                                        .filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)
//                                        .mapToObj(b ->
//                                                new int[]{a, b, (int)Math.sqrt(a * a + b * b)})
//                        );
//
//        pythagoreanTriples.forEach(t-> System.out.println(t[0]+","+t[1]+","+t[2]));
    }

    /**
     * flatMap示例
     */
    public static void useFlatMap(){
        List<String>uniqueChars = words.stream()
                // 将每个单词转换为由其字母构成的数组
                .map(w->w.split(""))
                //将各个生成流扁平化为单个流
                .flatMap(Arrays::stream)
//                .map(Arrays::stream) //输出结果类型是：<Stream<Stream<String>>
                .distinct()
                .collect(Collectors.toList());

        System.out.println(StringUtils.join(uniqueChars,","));
    }

    /**
     * 给定一个数字列表，如何返回一个由每个数的平方构成的列表呢?例如，给定[1, 2, 3, 4,
     * 5]，应该返回[1, 4, 9, 16, 25]。
     */
    public static void test1(){
        List<Integer>numbers = Arrays.asList(1,2,3,4,5);
        List<Integer>result = numbers.stream()
                .map(a->a*a)
                .collect(Collectors.toList());

        System.out.println(Arrays.toString(result.toArray()));
    }

    /**
     * 返回所有的数对。例如，给定列表[1, 2, 3]和列表[3, 4]，应该返回[(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]
     * 你可以使用两个map来迭代这两个列表，并生成数对。但这样会返回一个Stream<Stream<Integer[]>>。
     * 你需要让生成的流扁平化，以得到一个Stream<Integer[]>。这正是flatMap所做的:
     */
    public  static void retunAllNumbers(){
        List<Integer>numbers1 = Arrays.asList(1,2,3);
        List<Integer>numbers2 = Arrays.asList(3,4);
        List<int[]> arrayList = numbers1.stream()
                .flatMap(
                        a->numbers2.stream()
                                .map(b->new int[]{a,b}))
                .collect(Collectors.toList());
        System.out.println(Arrays.toString(arrayList.toArray()));
    }
}
