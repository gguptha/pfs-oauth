package com.sap.pfs.oauth.util;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * Created by fahadfazil on 22/01/17.
 */
public class CustomException extends RuntimeException implements Serializable {

    private String message;

    private HttpStatus httpStatus;

    public CustomException(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}