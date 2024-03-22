package com.debezium.debeziumdemo.exception;

import com.debezium.debeziumdemo.exception.product.ProductAlreadyExistsException;
import com.debezium.debeziumdemo.exception.product.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ProductNotFoundException.class})
    public ResponseEntity<ExceptionMessage> handleProductNotFoundException(ProductNotFoundException exception ){
        final ExceptionMessage exceptionMessage = new ExceptionMessage();
        exceptionMessage.setMessage(exceptionMessage.getMessage());
        exceptionMessage.setStatus(404);
        exceptionMessage.setReason(exceptionMessage.getReason());
        return new ResponseEntity<>(exceptionMessage, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler({ProductAlreadyExistsException.class})
    public ResponseEntity<ExceptionMessage> handleProductAlreadyExistsException(ProductAlreadyExistsException exception ){
        final ExceptionMessage exceptionMessage = new ExceptionMessage();
        exceptionMessage.setMessage(exceptionMessage.getMessage());
        exceptionMessage.setStatus(404);
        exceptionMessage.setReason(exceptionMessage.getReason());
        return new ResponseEntity<>(exceptionMessage, HttpStatus.NOT_FOUND);
    }


}
