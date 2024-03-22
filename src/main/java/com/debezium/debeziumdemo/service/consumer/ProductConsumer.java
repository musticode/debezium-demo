package com.debezium.debeziumdemo.service.consumer;

import com.debezium.debeziumdemo.constant.KafkaConstant;
import com.debezium.debeziumdemo.event.ProductMessage;
import com.debezium.debeziumdemo.model.Product;
import com.debezium.debeziumdemo.model.ProductEs;
import com.debezium.debeziumdemo.service.ProductService;
import com.debezium.debeziumdemo.service.impl.ProductEsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
@Slf4j
public class ProductConsumer {

    private final ProductEsServiceImpl productEsService;
    private final ProductService productService;

    public ProductConsumer(ProductEsServiceImpl productEsService, ProductService productService) {
        this.productEsService = productEsService;
        this.productService = productService;
    }


    @KafkaListener(
            topics = KafkaConstant.PRODUCT_CDC_TOPIC_NAME,
            groupId = KafkaConstant.PRODUCT_GROUP_ID
    )
    public void consume(ProductMessage message){

        log.info("Debezium event consumed : {}", message);

        if (message.getOp().equals("u")){
            log.info("UPDATE");

            Product product = productService.findProductById(message.getBefore().getId()); //before'dan gelen product'ın id'si

            Product updatedProduct = new Product();
            updatedProduct.setName(message.getAfter().getName());
            updatedProduct.setPrice(new BigDecimal(message.getAfter().getPrice()));
            updatedProduct.setStock(message.getAfter().getStock());

            log.info("Updated product : {}", updatedProduct);

            productEsService.updateProduct(
                    String.valueOf(product.getId()),
                    updatedProduct
            );


        } else if (message.getOp().equals("c")) {
            log.info("CREATE");

            final Product product = new Product();
            product.setName(message.getAfter().getName());
            product.setPrice(new BigDecimal(message.getAfter().getPrice()));
            product.setStock(message.getAfter().getStock());
            productEsService.save(product);

        }else {
            log.info("DELETE");

            final ProductEs productEs = productEsService.findProductById(String.valueOf(message.getAfter().getId()));
            productEsService.deleteProduct(productEs.getId());
        }



    }


    @KafkaListener(topics = "product_topic", groupId = "product-group")
    public void listen(Product message) {
        log.info("message : {}", message);
        // Process the received message
    }






}