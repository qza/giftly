package org.koko.resource.store.s3;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class S3RestAPIErrorHandler {

    @ExceptionHandler(value = IOException.class)
    public ResponseEntity<S3RestAPIResponse> handleAuthorizationException(IOException cause) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(S3RestAPIResponse.error("server error"));
    }
}
