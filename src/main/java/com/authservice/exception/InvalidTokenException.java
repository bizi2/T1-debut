package com.authservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED) // 401 Unauthorized
public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException(String message) {
        super(message);
    }

    public InvalidTokenException(String token, String reason) {
        super(String.format("Token '%s...' is invalid: %s",
                token.substring(0, Math.min(10, token.length())),
                reason));
    }
}