package com.debezium.debeziumdemo.service.consumer;

import com.debezium.debeziumdemo.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class ProductEventProducer {

    private final NewTopic topic;
    private final KafkaTemplate<String, Product> kafkaTemplate;

    private ProductEventProducer(NewTopic topic, KafkaTemplate<String, Product> kafkaTemplate){
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Product event){
        log.info(String.format("Product event :  %s", event.toString()));

        // create message
        Message<Product> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();

        kafkaTemplate.send(message);
    }



//
//    private final NewTopic topic;
//    private final KafkaTemplate<String, Product> kafkaTemplate;
//
//    private ProductEventProducer(NewTopic topic, KafkaTemplate<String, Product> kafkaTemplate){
//        this.topic = topic;
//        this.kafkaTemplate = kafkaTemplate;
//    }
//
//    public void sendMessage(Product event){
//        log.info("Product message : ", event);
//
//        // create message
//        Message<Product> message = MessageBuilder
//                .withPayload(event)
//                .setHeader(KafkaHeaders.TOPIC, topic.name())
//                .build();
//
//        kafkaTemplate.send(message);
//    }

}
