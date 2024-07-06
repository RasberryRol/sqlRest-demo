package com.sqlCloudVendor.sqlRestdemo.exceptions;

import org.springframework.http.HttpStatus;

public class CloudVendorException {



    private final String message; //user message
    private final Throwable throwable; //cause
    private final HttpStatus httpStatus; //status

    public CloudVendorException(String message, Throwable throwable, HttpStatus httpStatus) {
        this.message = message;
        this.throwable = throwable;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
