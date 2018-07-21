package com.violetbutterfly.drinkoff.web.error;

import org.springframework.security.access.AccessDeniedException;

public class ForbiddenAccessException extends AccessDeniedException {

    public ForbiddenAccessException(String msg) {
        super(msg);
    }

    public ForbiddenAccessException(String s, Throwable throwable) { super(s, throwable);}
}
