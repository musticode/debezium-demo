package com.debezium.debeziumdemo.service.impl;

import com.debezium.debeziumdemo.model.Product;
import com.debezium.debeziumdemo.model.ProductEs;
import com.debezium.debeziumdemo.repository.ProductElasticRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ProductEsServiceImpl  {


    private final ProductElasticRepository productElasticRepository;

    public ProductEsServiceImpl(ProductElasticRepository productElasticRepository) {
        this.productElasticRepository = productElasticRepository;
    }

    public void save(Product product){
        final ProductEs productEs = new ProductEs();

        //random uuid vermek yerine product objesinin long türündeki değerini string'e çevir
        //productEs.setId(UUID.randomUUID().toString());
        productEs.setId(String.valueOf(product.getId()));
        productEs.setName(product.getName());
        productEs.setPrice(product.getPrice());
        productEs.setStock(product.getStock());

        productElasticRepository.save(productEs);
    }

    public ProductEs updateProduct( String productId, Product product){

        if (productId.isBlank() ||productId.isEmpty()){
            log.warn("Product is not found with id : {}", productId);
            throw new RuntimeException("Product id is not found");
        }

        final ProductEs productEs = findProductById(productId);
        productEs.setName(product.getName());
        productEs.setStock(product.getStock());
        productEs.setPrice(new BigDecimal(product.getStock()));
        productElasticRepository.save(productEs);

        log.info("Updated : {}", productEs);
        return productEs;
    }

    public ProductEs findProductById(String productId){
        return productElasticRepository.findById(productId).orElseThrow(()-> new RuntimeException("Not found"));
    }

    public List<ProductEs> findAllProducts(){
        return (List<ProductEs>) productElasticRepository.findAll();
    }

    public void deleteProduct(String productId){
        final ProductEs productEs = findProductById(productId);
        productElasticRepository.delete(productEs);
        log.info("Product is deleted : {}", productEs);
    }

}
