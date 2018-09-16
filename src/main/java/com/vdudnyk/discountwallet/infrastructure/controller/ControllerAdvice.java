package com.vdudnyk.discountwallet.infrastructure.controller;

import com.vdudnyk.discountwallet.application.shared.ApiException;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ApiException.class})
    protected ResponseEntity<Object> handle(ApiException e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handle(Exception e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
    }

    @Value
    private class ErrorResponse {
        private String status;
    }
}
