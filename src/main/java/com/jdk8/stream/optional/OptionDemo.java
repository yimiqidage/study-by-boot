package com.jdk8.stream.optional;

import javax.swing.text.html.Option;
import java.util.Optional;

/**
 * Optional语法测试类
 * 1、使用Optional<T>类型，表明该对象可以为空，并且为空时，不会抛出 NullPointerException；
 * 2、如果对象为空，打印输出时，会显示为字符"null"；
 */
public class OptionDemo {
    public static void main(String[] args) {
        // 打印结果：result：null
        System.out.println("result："+new Person().getCar());

        optionalUseDemo();
    }

    /**
     * Optional语法，用法示例
     */
    public static void optionalUseDemo(){

        Car car = new Car();
        // 1、演示Optional声明语法
        //创建一个空的Optional 对象，使用 Optional.empty()
        Optional<Car> optionalCar = Optional.empty();
        //创建一个非空对象，使用 Optional.of() 方法；
        //如果值car为空，则会抛出 NullPointerException
        Optional<Car> ofCar = Optional.of(car);
        //创建一个可为空的对象，使用 Optional.ofNullable()方法
        // 创建一个允许null值的Optional 对象，如果car是null，那么得到的Optional对象就是个空对象
        Optional<Car> nullCar = Optional.ofNullable(new Car());

        // 2、如果Optional对象为空，不能直接调用对象的get方法，否则仍然会抛出 NullPointerException
        // 此时应该使用orElse()方法，并设置默认值；
//        Insurance insurance = new Insurance("BYD");
        Insurance insurance = new Insurance(null);
        Optional<String> insuranceName = Optional.ofNullable(insurance).map(Insurance::getName);
        System.out.println("insuranceName:"+insuranceName.orElse(""));
        // 3、空的Optional对象，调用get方法，会抛出 NullPointerException
        System.out.println(insuranceName.get());

        Optional<Person> person = Optional.ofNullable(null);
        Optional<String>names = person.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName);

        System.out.println("Optional<String>names："+names.orElse(""));

    }
}


 class Person {
    private Optional<Car> car;
    public Optional<Car> getCar() { return car; }
}
 class Car {
    private Optional<Insurance> insurance;
    public Optional<Insurance> getInsurance() { return insurance; }
}

/**
 * Insurance是保险的意思
 */
 class Insurance {
    private String name;
    public String getName() { return name; }
    Insurance(String name){
        this.name = name;
    }
}

