package com.jms;

import com.annotations.repeatable.Author;
import com.jms.springio.demo.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Creator weishi8
 * Date&Time 2019-07-11 14:07
 * description
 */
@Controller
public class BeanTestController {

    @Autowired
    private JmsTemplate jmsTemplate;

    @ResponseBody
    @GetMapping("/jms/test")
    public String test(Bean2 bean2){
        System.out.println("bean2");
        jmsTemplate.convertAndSend("mailbox", new Email("bean2", "Hello,bean2"));
        return "test";
    }
}

@Component
class Bean1{
    private String str;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public Bean1(){
        this.str="abc";
        System.out.println("初始化bean1：");
    }
}

class Bean2{
    private String str;
    private Bean1 bean1;

    public Bean1 getBean1() {

        return bean1;
    }

    public void setBean1(Bean1 bean1) {
        this.bean1 = bean1;
    }

    public Bean2(){
        this.str="abc2";
        System.out.println("初始化bean2：");
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return "Bean2{" +
                "str='" + str + '\'' +
                ", bean1=" + bean1.getStr() +
                '}';
    }
}