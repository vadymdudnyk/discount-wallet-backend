package com.vdudnyk.discountwallet.application.shared;

public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}
