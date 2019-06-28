package com.jdk8.stream.usestream;

import com.jdk8.stream.function.pojo.Dish;
import com.jdk8.stream.function.pojo.Trader;
import com.jdk8.stream.function.pojo.Transaction;
import org.apache.commons.lang.StringUtils;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 用流收集数据
 */
public class CollectDateDemo {

    public static List<Transaction>transactions = null;

    static {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");

       transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    public static  List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),  new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH), new Dish("salmon", false, 450, Dish.Type.FISH) );


    public static void main(String[] args) {


    Map<Integer,List<Transaction>> mapTrans = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getYear));
        System.out.println(mapTrans);

    IntSummaryStatistics intSummaryStatistics = menu.stream().
            collect(Collectors.summarizingInt(Dish::getCalories));

        System.out.println(intSummaryStatistics);

        IntStream intStream = IntStream.generate(()-> {return (int)(Math.random()*100);}).limit(5);
        IntSummaryStatistics stats = intStream.collect(IntSummaryStatistics::new,
                IntSummaryStatistics::accept,
                IntSummaryStatistics::combine);
        System.out.println(stats);


        List<Dish> filterList = menu.stream()
                .filter( (Dish dish) -> {return !dish.isVegetarian();}).collect(Collectors.toList());

        Predicate<Dish>predicate = Dish::isVegetarian;
        List<Dish> filterList2 = menu.stream()
                .peek(dish -> System.out.println("before："+dish.getName()))
                .filter( predicate.negate())
                .peek(dish-> System.out.println("after:"+dish.getName()))
                .collect(Collectors.toList());
        System.out.println("filterList2:"+StringUtils.join(filterList,","));

//        menu.stream().parallel()

        List<String>names = new ArrayList<>();
        menu.forEach((Dish dish)-> names.add(dish.getName()));
        System.out.println(StringUtils.join(names,","));

//        Comparator<Dish> comparator = Comparator.comparing(Dish::getName).thenComparing(Dish::getCalories);
        System.out.println(StringUtils.join(menu,","));
        menu.sort(Comparator.comparing(Dish::getName).thenComparing(Dish::getCalories));
        System.out.println(StringUtils.join(menu,","));
    }



}
