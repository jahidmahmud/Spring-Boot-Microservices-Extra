package com.productservice.controller;

import com.productservice.dto.ProductRequest;
import com.productservice.dto.ProductResponse;
import com.productservice.models.Product;
import com.productservice.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductService service;

    @PostMapping("/")
    public ResponseEntity<ProductResponse> addStudents(@RequestBody ProductRequest productRequest) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.addProduct(productRequest));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        try {
            return ResponseEntity.ok().body(service.getAllProducts());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.noContent().build();
    }
}
