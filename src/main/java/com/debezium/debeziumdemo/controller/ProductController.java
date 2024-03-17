package com.debezium.debeziumdemo.controller;

import com.debezium.debeziumdemo.dto.ProductDto;
import com.debezium.debeziumdemo.model.Product;
import com.debezium.debeziumdemo.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {


    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping("/add-product")
    public ResponseEntity<Product> addProduct(@RequestBody ProductDto request){
        return new ResponseEntity<>(productService.addProduct(request), HttpStatus.CREATED);
    }

    @GetMapping("/all-products")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }


    @PutMapping("/update-product/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @RequestBody Product request){
      return new ResponseEntity<>(productService.updateProduct(productId, request), HttpStatus.OK);
    }



}
