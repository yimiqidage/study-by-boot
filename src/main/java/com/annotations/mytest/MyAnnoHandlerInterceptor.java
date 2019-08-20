package com.annotations.mytest;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Creator weishi8
 * Date&Time 2019-08-16 11:56
 * description
 */
@Component
public class MyAnnoHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AnnotationTest annotationTest =  handler.getClass().getAnnotation(AnnotationTest.class);
        if(annotationTest!=null){
            System.out.println("annotationTest.name():"+annotationTest.name());
        }
        System.out.println("annotationTest.name() is null");
        return false;
    }
}
