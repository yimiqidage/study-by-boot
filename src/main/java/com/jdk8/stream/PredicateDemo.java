package com.jdk8.stream;

import com.jdk8.stream.interfaces.LambdaInterface;
import org.apache.commons.lang.StringUtils;

import java.util.*;
import java.util.function.Predicate;

/**
 * @author weishi8
 * @create 2019-06-13
 * @description 使用谓词，实现函数式编程，简化开发流程
 * 1、谓词(predicate) 在数学上常常用来代表一个类似函数的东西，它接受一个参数值，并返回true或false。
 * 2、使用谓词，将方法做为参数，进行传递。
 * 3、如果逻辑比较复杂，还是应该抽象为具体方法，而不是用lambda；
 */
public class PredicateDemo {

    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(new Apple("green",160),new Apple("yellow",50));

        System.out.println("使用 Apple::isHeavyApple 方式："+StringUtils.join(predicate(apples),","));
        System.out.println("使用lambda方式："+StringUtils.join(lambda(apples),","));

    }

    /**
     * 1、传递方法：把方法做为参数（适合比较共通，利于抽象出的内容）
     * @param apples
     * @return
     */
    public static List<Apple> predicate(List<Apple> apples){
       return  filterApples(apples,Apple::isHeavyApple);
    }

    /**
     * 2、使用lambda：只用一两次的代码，无需抽象为方法，直接写逻辑
     * @param apples
     * @return
     */
    public static List<Apple> lambda(List<Apple> apples){
        return  filterApples(apples,(Apple apple )-> apple.getWeight() > 150);
    }


    /**
     * 3、带有谓词做为参数的函数
     * @param inventory list结合对象
     * @param p  接收一个函数，并返回true或false
     * @return
     */
    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p){
        List<Apple> result = new ArrayList<>();
        for (Apple apple: inventory){ if (p.test(apple)) {
            result.add(apple);
        }
        }
        return result;
    }
}
class Apple{


    /**
     * 克隆Apple对象，将当前对象，拷贝给apple
     * @param apple Apple对象实例
     * @return apple
     */
    public Apple cloneApple(Apple apple){
        apple.setColor(this.getColor());
        apple.setWeight(this.getWeight());
        return apple;
    }
    private String color;
    private Integer weight;

    public static boolean isGreenApple(Apple apple) {
        return "yellow".equals(apple.getColor());
    }
    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }

    public Apple (String color,Integer weight){
        this.color=color;
        this.weight = weight;
    }

    public Apple(){}

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }
}