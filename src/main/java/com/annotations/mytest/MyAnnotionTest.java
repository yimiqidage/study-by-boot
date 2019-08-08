
package com.annotations.mytest;

import org.apache.commons.lang.StringUtils;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@AnnotationTest(name = "zhangsan",age = 20)
public class MyAnnotionTest {

    private String name;
    private int age ;

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

    public MyAnnotionTest(@NonNull String  name, int age){
        this.name = name;
        this.age = age;
    }

    /**
     * 方法上使用注解
     * @return
     */
    @AnnotationConstructTest(returnType = MyAnnotionTest.class , paramCount = 2)
    public String toString2(){
        return "hello";
    }

    @Override
    public String toString() {
        return "MyAnnotionTest{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class Test{
    public static void main(String[] args) {
        AnnotationTest annotationTest = MyAnnotionTest.class.getAnnotation(AnnotationTest.class);
        int age = annotationTest.age();
        String name = annotationTest.name();
        System.out.println(String.format("name:%s,age:%s",name,String.valueOf(age)));

        Method[]methods = MyAnnotionTest.class.getDeclaredMethods();
        Arrays.asList(methods).stream().filter(m->m.getName().equals("toString2")).forEach(m->{
            AnnotationConstructTest annotationConstructTest = m.getAnnotation(AnnotationConstructTest.class);
            System.out.println("method toString2:"+ annotationConstructTest.paramCount()+","+annotationConstructTest.returnType());
        });
        for(Method method:methods){

        }
        System.out.println(Arrays.toString(methods));

        List<String>list = new ArrayList<>();
        Arrays.stream(methods)
                .filter(m->m.getDeclaredAnnotation(AnnotationConstructTest.class)!=null)
                .forEach(m->{
                    AnnotationConstructTest annotationConstructTest = m.getDeclaredAnnotation(AnnotationConstructTest.class);
                    list.add(String.join(",",String.valueOf(annotationConstructTest.paramCount()),annotationConstructTest.returnType().toString()));
                });
        System.out.println(StringUtils.join(list,","));
        MyAnnotionTest test = new MyAnnotionTest(null,1);
        System.out.println(test.toString());

        MyAnnotionTest test1 = new @NotNull MyAnnotionTest("lisi",4);

        Date date = new @NotNull Date();

        String s = new @AnnotationTest(name="string.test",age = 3) String("2");


    }
}
