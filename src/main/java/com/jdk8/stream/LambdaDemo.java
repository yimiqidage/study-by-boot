package com.jdk8.stream;

import com.jdk8.stream.interfaces.LambdaInterface;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**˜
 * @author weishi8
 * @create 2019-06-14
 * @description lambda表达式示例：
 * 演示基本语法：
 * 1、(parameters) -> expression
 * 2、(parameters) -> { statements; }
 *
 */
public class LambdaDemo {



    public Callable<String> fetch() {
        return () -> "Tricky example ;-)";
    }

    public LambdaInterface testLambda(){
        return (key1,key2) -> "ddd";
    }

    public static void main(String[] args) {

        Consumer<String>c1 = (str)->  System.out.println(str);

        Consumer<String>c2 = (str)->  System.out.println(str);


        c1.accept("a");
        c2.andThen(c1);

        LambdaTypeVal lambdaTypeVal = new LambdaTypeVal();
        lambdaTypeVal.methodReferenceDemo();
    }


    /**
     * 写法示例
     */
    public static void demo(){
        //1、正确写法一：如果是单行，不用写return语法（否则会报错）
        LambdaInterface interfaces1  = (key1,key2)-> (key1+","+key2);

        //2、错误写法一：单行，使用了return；
//        LambdaInterface interfaces_e1  = (key1,key2)-> return (key1+","+key2);

        //3、正确写法二：单行，并且使用{}包括起来，才能使用return；
        LambdaInterface interfaces2  =   (key1,key2)-> {return (key1+","+key2);};

        //4、正确写法三：如果是多行，需要用{}包含起来，并且要写return语法。
        LambdaInterface interfaces3  = (String key1, String key2)-> {
            System.out.println(key1+","+key2);
            return (key1+","+key2);
        };
    }
}

/**
 * 使用Function实现函数式编程
 */
class FunctionDemo{

    public static void main(String[] args) {
        functionDemo();


    }

    public static void functionDemo(){

        //1、定义一个String类型的list
        List<String>list = Arrays.asList("abc","abcd","abcde");

        //2、参数list的类型为List<String>，因此调用方法map时，对应的List<T>的T为String
        //3、(String str)-> str.length()中，通过查看Function的方法，发现返回类型为R，入参类型为T
        //4、因为lambda表达式，返回的str.length()为int类型，因此最终map方法返回的就是List<Integer>格式；
        List<Integer> intList = map(list,(String str)-> str.length());

        //5、调用工具类方法，将list转换为字符串
        System.out.println(StringUtils.join(intList,","));

    }

    /**
     * Function示例：
     * <T,R> 是定义用到的两个类型，T是入参list的类型，R是返回的类型；
     * @param list
     * @param f 函数式接口，调用具体的lambda表达式实现
     * @param <T> 入参类型
     * @param <R> 返回类型
     * @return
     */
    public static <T,R> List<R>map(List<T>list,Function<T,R> f){
        List<R>result = new ArrayList<>();
        for (T t:list) {
            result.add(f.apply(t));
        }
        return result;
    }


}

/**
 * lambda类型校验
 */
class LambdaTypeVal{

    public static final String str = "111";

    private Apple apple = new Apple("lambdaTypeValue",200);

    /**
     *
     */
    public void typeVal(){
        int portNumber = 133;
        Apple apple = new Apple("green",100);

        System.out.println(apple.getWeight());
        // 1、lambda内部类，可以使用类的实例变量和静态变量；
        // 2、使用的类的
        Runnable r = ()-> System.out.println(portNumber+str+apple.getWeight());
//            portNumber = 1334;
    }

    /**
     * lambda表达式方法引用demo
     */
    public void methodReferenceDemo(){

        List<String>list = Arrays.asList("a","b","A","B");
        list.sort(String::compareToIgnoreCase);

        List<String>list2 = Arrays.asList("a","b","A","B");
        list2.sort((str1,str2)->str1.compareToIgnoreCase(str2));

        //1、指向静态方法的引用：调用Integer.parseInt(String str)
        Consumer<String>consumer = Integer::parseInt;

        //1等价于如下代码：
        Consumer<String>consumer10 = (String str)->Integer.parseInt(str);

        //2、指向实例方法的引用：调用String
        Consumer<String>consumer2 = String::length;

        //2等价于如下代码
        Consumer<String>consumer20 = (String str)->str.length();

        //3、指向现有对象的实例方法的引用:调用apple.cloneApple方法
        Consumer<Apple> consumer3 = apple::cloneApple;
//        Apple apple1 = new Apple();
//        consumer3.accept(apple1);
//        System.out.println("apple::cloneApple:"+apple1.getColor()+","+apple1.getWeight());

        //3等价于如下代码
        UnaryOperator<Apple> consumer30 = (Apple apple2) -> apple.cloneApple(apple2);
//        Apple apple2 = new Apple();
//        consumer30.apply(apple2);
//        System.out.println("(Apple apple2) -> apple.cloneApple(apple2):"+apple2.getColor()+","+apple2.getWeight());

        //3-1、调用Apple实例的setColor(String color) 方法
        Consumer<String> consumer4 = apple::setColor;
//        consumer4.accept("consumer4");
//        System.out.println("apple::setColor:"+apple.getColor());

        //3-2、调用Apple实例的setWeight(int weight)方法
        Consumer<Integer> consumer5 = apple::setWeight;

    }

}