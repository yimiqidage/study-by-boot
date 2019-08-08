package com.jms;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.ConnectionFactory;

/**
 * Creator weishi8
 * Date&Time 2019-07-11 14:18
 * description
 */
@Configuration
public class JmsConfiguration {

    @Bean
    public Bean2 bean21(Bean1 bean1){
        Bean2 bean2 = new Bean2();
        bean2.setBean1(bean1);
        bean2.setStr("JmsConfiguration:bean21:str");
        System.out.println("bean2:"+bean2.toString());
        return bean2;
    }

}
