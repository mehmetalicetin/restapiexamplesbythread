package com.spring.web.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {Exception.class, MessageServiceException.class})
    public ResponseEntity<Object> handlerAnyException(Exception exception, WebRequest webRequest){
        //return new ResponseEntity<>(exception, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

        String message = exception.getLocalizedMessage();
        if(message == null) message = exception.toString();

        ErrorMessage errorMessage = new ErrorMessage(message, new Date());

        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

   /* @org.springframework.web.bind.annotation.ExceptionHandler(value = MessageServiceException.class)
    public ResponseEntity<Object> handlerMessageServiceException(MessageServiceException messageServiceException, WebRequest webRequest){
        String message = messageServiceException.getLocalizedMessage();
        if(message == null) message = messageServiceException.toString();

        ErrorMessage errorMessage = new ErrorMessage(message, new Date());

        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }*/
}
