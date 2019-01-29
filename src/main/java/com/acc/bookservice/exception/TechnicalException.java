package com.acc.bookservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Handles any other technical exception like network issues
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class TechnicalException extends RuntimeException {

    public TechnicalException(String message) {
        super(message);
    }
}
