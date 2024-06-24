package com.kris.acg.enums;

import org.springframework.http.HttpMethod;

public enum MyHttpMethod {
    GET,
    POST,
    PUT,
    DELETE,
    ALL;

    public static MyHttpMethod from(String method) {
        try {
            return MyHttpMethod.valueOf(method);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
