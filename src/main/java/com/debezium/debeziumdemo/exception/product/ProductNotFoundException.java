package com.debezium.debeziumdemo.exception.product;

public class ProductNotFoundException extends RuntimeException{
    private String message;
    public ProductNotFoundException(String message){
        super(message);
    }

}
