package com.debezium.debeziumdemo.deserializer;

import com.debezium.debeziumdemo.event.ProductMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;


import java.util.Map;


public class ProductMessageDeserializer implements Deserializer<ProductMessage> {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public ProductMessage deserialize(String topic, byte[] data) {

        try {
            return objectMapper.readValue(data, ProductMessage.class);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }


    @Override
    public ProductMessage deserialize(String topic, Headers headers, byte[]data){
        return Deserializer.super.deserialize(topic, headers, data);
    }


    @Override
    public void close() {
        Deserializer.super.close();
    }


}
