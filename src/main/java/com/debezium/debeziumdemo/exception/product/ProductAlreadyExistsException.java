package com.debezium.debeziumdemo.exception.product;

public class ProductAlreadyExistsException extends RuntimeException{
    private String message;
    public ProductAlreadyExistsException(String message){
        super(message);
    }
}
