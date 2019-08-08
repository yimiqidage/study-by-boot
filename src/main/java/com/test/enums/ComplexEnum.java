package com.test.enums;

/**
 * Creator weishi8
 * Date&Time 2019-07-05 15:34
 * description 定义复杂的枚举类：添加自定义构造器
 */
public  enum ComplexEnum {

    ZHANGSAN("zhangsan",20),
    LISI("lisi",23);

    private String name;
    private int age;

    /**
     * 添加自定义构造器
     * 枚举构造器，只能是私有的
     * @param name
     * @param age
     */
    ComplexEnum(String name,int age){
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        super.toString();
        return this.getName();
    }





}
