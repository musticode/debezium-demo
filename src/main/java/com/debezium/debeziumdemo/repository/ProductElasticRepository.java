package com.debezium.debeziumdemo.repository;

import com.debezium.debeziumdemo.model.ProductEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductElasticRepository extends ElasticsearchRepository<ProductEs, String> {
}
