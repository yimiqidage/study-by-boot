package com.spring4.rest.server;

/**
 * Creator weishi8
 * Date&Time 2019-07-09 18:03
 * description
 */
public class RestNotFoundException extends RuntimeException {
    private String errorName;

    public RestNotFoundException(String errorName) {
        this.errorName = errorName;
    }

    public String getErrorName() {
        return errorName;
    }
}
