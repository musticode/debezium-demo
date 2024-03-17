package com.debezium.debeziumdemo.service.consumer;

import com.debezium.debeziumdemo.event.ProductMessage;
import com.debezium.debeziumdemo.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class ProductConsumer {

    public static final String PRODUCT_CDC_TOPIC_NAME = "product.public.product";

    @KafkaListener(
            topics = PRODUCT_CDC_TOPIC_NAME,
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(ProductMessage message){
        log.info("Event consumeeedd : {}", message);

        if (message.getOp().equals("u")){
            log.info("UPDATE");
        } else if (message.getOp().equals("c")) {
            log.info("CREATE");
        }
    }


    @KafkaListener(topics = "product_topic", groupId = "product-group")
    public void listen(Product message) {
        log.info("message : {}", message);
        // Process the received message
    }






}