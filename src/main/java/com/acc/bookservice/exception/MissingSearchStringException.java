package com.acc.bookservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception class deals with missing search params
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class MissingSearchStringException extends RuntimeException {

    public MissingSearchStringException(String message) {
        super(message);
    }
}
