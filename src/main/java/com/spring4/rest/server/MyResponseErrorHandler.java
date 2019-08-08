package com.spring4.rest.server;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Creator weishi8
 * Date&Time 2019-07-10 13:53
 * description
 */
public class MyResponseErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        int httpStatus = response.getStatusCode().value();
        List<Integer> list = Arrays.asList(HttpStatus.OK.value(),HttpStatus.NOT_FOUND.value());
        System.out.println("服务端返回状态："+httpStatus);
        if(list.contains(httpStatus)){
            return false;
        }
        return true;
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        System.out.println("error:"+response.toString());
    }
}
