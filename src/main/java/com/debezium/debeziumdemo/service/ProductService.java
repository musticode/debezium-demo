package com.debezium.debeziumdemo.service;

import com.debezium.debeziumdemo.dto.ProductDto;
import com.debezium.debeziumdemo.model.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(ProductDto product);
    List<Product> getAllProducts();

    Product updateProduct(long productId, Product updatedProduct);
}
