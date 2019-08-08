package com.spring4.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

/**
 * Creator weishi8
 * Date&Time 2019-07-08 16:50
 * description
 */
@Configuration
@ComponentScan(basePackages={"com.spring4.rest.config"})
public class RestConfig {

//    @Bean
//    public ViewResolver cnViewResolver(){
//        return new ContentNegotiatingViewResolver();
//    }


}
