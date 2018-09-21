package com.violetbutterfly.drinkoff.api.exception;

public class GeneralApiError extends RuntimeException {

    public GeneralApiError(String msg) {
        super(msg);
    }

    public GeneralApiError(String s, Throwable throwable) {
        super(s, throwable);
    }
}
