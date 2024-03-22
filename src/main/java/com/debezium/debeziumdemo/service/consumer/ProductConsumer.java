package com.debezium.debeziumdemo.service.consumer;

import com.debezium.debeziumdemo.constant.KafkaConstant;
import com.debezium.debeziumdemo.event.ProductMessage;
import com.debezium.debeziumdemo.model.Product;
import com.debezium.debeziumdemo.model.ProductEs;
import com.debezium.debeziumdemo.service.impl.ProductEsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;


@Service
@Slf4j
public class ProductConsumer {

    private final ProductEsServiceImpl productEsService;

    public ProductConsumer(ProductEsServiceImpl productEsService) {
        this.productEsService = productEsService;
    }


    @KafkaListener(
            topics = KafkaConstant.PRODUCT_CDC_TOPIC_NAME,
            groupId = KafkaConstant.PRODUCT_GROUP_ID
    )
    public void consume(ProductMessage message){
        log.info("Event consumeeedd : {}", message);

        if (message.getOp().equals("u")){
            log.info("UPDATE");

            //es'ten id ile bak, sonrasında o id ile update et
            //productEsService.updateProduct()



        } else if (message.getOp().equals("c")) {
            log.info("CREATE");

            final Product product = new Product();
            product.setName(message.getAfter().getName());
            product.setPrice(new BigDecimal(message.getAfter().getPrice()));
            product.setStock(message.getAfter().getStock());
            productEsService.save(product);
        }
    }


    @KafkaListener(topics = "product_topic", groupId = "product-group")
    public void listen(Product message) {
        log.info("message : {}", message);
        // Process the received message
    }






}