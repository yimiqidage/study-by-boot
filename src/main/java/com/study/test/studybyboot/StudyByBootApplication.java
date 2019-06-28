package com.study.test.studybyboot;

import com.emails.SendEmail;
import com.jdk8.stream.future.CommonFutureDemo;
import com.jdk8.stream.future.CompletableFutureDemo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudyByBootApplication {


    private SendEmail sendEmail;

    private CompletableFutureDemo completableFutureDemo;

    public static void main(String[] args) {
        SpringApplication.run(StudyByBootApplication.class, args);

    }

}
