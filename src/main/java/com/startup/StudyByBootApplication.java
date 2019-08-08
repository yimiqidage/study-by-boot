package com.startup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@ComponentScan(basePackages = "com.*")
public class StudyByBootApplication  {


    @RequestMapping("/")
    public String index(){
        return "Hello Spring Boot";
    }


    public static void main(String[] args) {
        SpringApplication.run(StudyByBootApplication.class, args);

    }

//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        super.configureMessageConverters(converters);
//        converters.add(new FormHttpMessageConverter());
//    }

}
