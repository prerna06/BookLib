package com.acc.bookservice.controller;

import com.acc.bookservice.dto.ExceptionDto;
import com.acc.bookservice.exception.BookNotFoundException;
import com.acc.bookservice.exception.InvalidDataException;
import com.acc.bookservice.exception.MissingSearchStringException;
import com.acc.bookservice.exception.TechnicalException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedExceptionController extends ResponseEntityExceptionHandler {

   /* @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException(Exception e, WebRequest webRequest){
        ExceptionDto exceptionDto = new ExceptionDto(new Date(), e.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity(exceptionDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }*/

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(BookNotFoundException e, WebRequest webRequest) {
        return new ResponseEntity(new ExceptionDto(new Date(), e.getMessage(), webRequest.getDescription(false)), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingSearchStringException.class)
    public ResponseEntity<Object> handleNotFoundException(MissingSearchStringException e, WebRequest webRequest) {
        return new ResponseEntity(new ExceptionDto(new Date(), e.getMessage(), webRequest.getDescription(false)), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<Object> handleInvalidDataException(InvalidDataException e, WebRequest webRequest) {
        return new ResponseEntity(new ExceptionDto(new Date(), e.getMessage(), webRequest.getDescription(false)), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TechnicalException.class)
    public ResponseEntity<Object> handleInvalidDataException(TechnicalException e, WebRequest webRequest) {
        return new ResponseEntity(new ExceptionDto(new Date(), e.getMessage(), webRequest.getDescription(false)), HttpStatus.BAD_REQUEST);
    }
}
