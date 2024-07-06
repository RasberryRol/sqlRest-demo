package com.sqlCloudVendor.sqlRestdemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice //handle exceptions globally across the application
public class CloudVendorExcpHandler {

    //to handle just cloudVendorNotFoundException
    @ExceptionHandler(value = {CloudVendorNotFoundExcp.class})
    public ResponseEntity<Object> handleCloudVendorNotFoundExcp(CloudVendorNotFoundExcp cloudVendorNotFoundExcp){
        
        CloudVendorException cloudVendorException = new CloudVendorException(
                                                                            cloudVendorNotFoundExcp.getMessage(),
                                                                            cloudVendorNotFoundExcp.getCause(),
                                                                            HttpStatus.NOT_FOUND
                                                                        );

        return new ResponseEntity<>(cloudVendorException, HttpStatus.NOT_FOUND);
    }
}
