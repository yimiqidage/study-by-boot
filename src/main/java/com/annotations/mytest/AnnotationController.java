package com.annotations.mytest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Creator weishi8
 * Date&Time 2019-08-16 13:58
 * description
 */
@RestController
@AnnotationTest(name="zhagnsan",age=10)
public class AnnotationController {

    private String name;

    @ResponseBody
    @GetMapping("/anno/test")
    public String test(){
        name="ddd";
        System.out.println("annotationTest");
        return name;
    }

}
