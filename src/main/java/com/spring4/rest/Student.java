package com.spring4.rest;

import javax.validation.Valid;

/**
 * Creator weishi8
 * Date&Time 2019-07-08 17:02
 * description
 */
public class Student {

    public Student(){

    }
    public Student(String name,int age){
        this.name = name;
        this.age = age;
    }

    @Valid
    private String name;
    @Valid
    private int age;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
