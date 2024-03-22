package com.debezium.debeziumdemo.service.impl;

import com.debezium.debeziumdemo.dto.ProductDto;
import com.debezium.debeziumdemo.exception.product.ProductNotFoundException;
import com.debezium.debeziumdemo.model.Product;
import com.debezium.debeziumdemo.repository.ProductRepository;
import com.debezium.debeziumdemo.service.ProductService;
import com.debezium.debeziumdemo.service.consumer.ProductEventProducer;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;
    private final ProductEventProducer productEventProducer;

    public ProductServiceImpl(ProductRepository productRepository, ProductEventProducer productEventProducer) {
        this.productRepository = productRepository;
        this.productEventProducer = productEventProducer;
    }


    @Override
    @Transactional
    public Product addProduct(ProductDto product){
        final Product newProduct = new Product();
        newProduct.setStock(product.getStock());
        newProduct.setName(product.getName());
        newProduct.setPrice(product.getPrice());
        productEventProducer.sendMessage(newProduct);
        return  productRepository.save(newProduct);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(long productId, Product updatedProduct) {
        final Product currentProduct = productRepository.findById(productId).orElseThrow(()-> new ProductNotFoundException("No currentProduct with id"));
        currentProduct.setName(updatedProduct.getName());
        currentProduct.setPrice(updatedProduct.getPrice());
        currentProduct.setStock(updatedProduct.getStock());
        return productRepository.save(currentProduct);
    }

    @Override
    public Product findProductById(long productId) {
        return productRepository.findById(productId).orElseThrow(()->new ProductNotFoundException("Product not found"));
    }


}
