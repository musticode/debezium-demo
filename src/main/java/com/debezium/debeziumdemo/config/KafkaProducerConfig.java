package com.debezium.debeziumdemo.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaProducerConfig {

    //@Value("${spring.kafka.topic.name}")
    private String topicName;

    // spring bean for kafka topic
    public static final String PRODUCT_TOPIC = "product_topic";

    @Bean
    public NewTopic topic(){
        return TopicBuilder.name(PRODUCT_TOPIC)
//                .partitions()
                .build();
    }

}
