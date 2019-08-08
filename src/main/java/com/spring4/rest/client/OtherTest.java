package com.spring4.rest.client;

import com.spring4.rest.Student;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Creator weishi8
 * Date&Time 2019-07-09 16:35
 * description
 */
public class OtherTest {
    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();
        String str = "";

        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
        headers.add("name","dddfadf");
        HttpEntity<String>entity = new HttpEntity<String>(str,headers);
       ResponseEntity<String> responseEntity =  restTemplate.exchange("http://127.0.0.1:8080/exception/get/simple/zhangsan",HttpMethod.GET,entity,String.class);
        System.out.println(responseEntity);

    }



    public static void testGet(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://127.0.0.1:8081/student/get/str";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.TEXT_PLAIN));
        HttpEntity<String>requestEntity = new HttpEntity<>(headers);
        ResponseEntity<Student> responseEntity = restTemplate.exchange(url, HttpMethod.GET,requestEntity,Student.class);
        System.out.println(url +":"+responseEntity.getBody());
    }


}
