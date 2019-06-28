package com.stack;

/**
 * @author weishi8
 * @create 2019-04-24
 * @description
 */
public class Student {
    Student(){

    }
    Student (String name,String grade){
        this.name = name;
        this.grade=grade;
    }
    private String name;
    private String grade;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
