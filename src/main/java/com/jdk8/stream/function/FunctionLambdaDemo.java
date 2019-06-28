package com.jdk8.stream.function;

import com.jdk8.stream.function.pojo.Dish;

import java.util.Arrays;
import java.util.List;
import static java.util.stream.Collectors.toList;

/**
 * 函数式数据处理
 */
public class FunctionLambdaDemo {

    public static  List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),  new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH), new Dish("salmon", false, 450, Dish.Type.FISH) );

    public static void main(String[] args) {


    }


    /**
     * 熟悉后的写法，比较简便
     * @param args
     */
    public static void functionStream(String []args){
        List<String> list = menu.stream()
                .filter(dish->dish.getCalories()>300)
                .map(Dish::getName)
                .limit(3)
                .collect(toList());

        System.out.println(list);
    }

    /**
     * 打印详细的流，执行过程，用于观察任务执行过程。
     * 打印结果：
     * filter:pork
     * map:pork
     * filter:beef
     * map:beef
     * filter:chicken
     * map:chicken
     * 可以看到，filter和map是两个独立的操作，但它们合并到同一次遍历中了。
     */
    public static void functionDetail(){
        List<String> list = menu.stream()
                .filter((Dish dish) -> {
                    System.out.println("filter:"+dish.getName());
                    return dish.getCalories()>100;
                })
                .map(dish -> {
                    System.out.println("map:"+dish.getName());
                    return dish.getName();
                })
                .limit(3)
                .collect(toList());
        System.out.println(list);
    }
}
