package com.productservice.service;

import com.productservice.dto.ProductRequest;
import com.productservice.dto.ProductResponse;
import com.productservice.models.Product;
import com.productservice.repository.ProductRepository;
import com.productservice.util.ProductUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Autowired
    ProductRepository productRepository;
    ProductUtil util = new ProductUtil();

    @Override
    public ProductResponse addProduct(ProductRequest productRequest) {
        try {
            return util.productToProductResponse(productRepository.save(util.productRequestToProduct(productRequest)));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return new ProductResponse();
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        try {
            List<Product> products = productRepository.findAll();
            return products.stream().map(util::productToProductResponse).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return Collections.singletonList(new ProductResponse());
    }

}
