package com.spring4.rest.client;

import com.spring4.rest.server.MyResponseErrorHandler;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Creator weishi8
 * Date&Time 2019-07-10 13:58
 * description 使用自定义ResponseErrorHandler
 */
public class RestTemplateUseMyResponseErrorHandler {
    public static void main(String[] args) {
        MyResponseErrorHandler handler = new MyResponseErrorHandler();
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(handler);
        String str = "";
        HttpEntity<String> entity = new HttpEntity<String>(str);
        ResponseEntity<String> responseEntity =  restTemplate.exchange("http://127.0.0.1:8080/exception/get/simple/zhangsandd", HttpMethod.GET,entity,String.class);
        System.out.println(responseEntity);
    }
}
