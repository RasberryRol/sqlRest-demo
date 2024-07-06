package com.sqlCloudVendor.sqlRestdemo.exceptions;

public class CloudVendorNotFoundExcp extends RuntimeException{
    public CloudVendorNotFoundExcp(String message) {
        super(message);
    }

    public CloudVendorNotFoundExcp(String message, Throwable cause) {
        super(message, cause);
    }
}
