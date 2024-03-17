package com.debezium.debeziumdemo.config;

import com.debezium.debeziumdemo.deserializer.ProductMessageDeserializer;
import com.debezium.debeziumdemo.model.Product;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProductConsumerConfig {



    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Product> kafkaListenerContainerFactory(ConsumerFactory<String, Product> consumerFactory){
        ConcurrentKafkaListenerContainerFactory<String, Product> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }


    @Bean
    public ConsumerFactory<String, Product> consumerFactory(){
        Map<String, Object> props = new HashMap<>();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("auto.offset.reset", "earliest");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "product-group");

        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", ProductMessageDeserializer.class.getName());

        return new DefaultKafkaConsumerFactory<>(props);
    }


}
