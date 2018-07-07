package com.violetbutterfly.drinkoff.api.exception;

import org.springframework.security.core.AuthenticationException;

public class UserAuthenticationException extends AuthenticationException {

    public UserAuthenticationException(String msg) {
        super(msg);
    }

    public UserAuthenticationException(String s, Throwable throwable) { super(s, throwable);}
}
