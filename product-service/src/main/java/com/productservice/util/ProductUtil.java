package com.productservice.util;

import com.productservice.dto.ProductRequest;
import com.productservice.dto.ProductResponse;
import com.productservice.models.Product;
import lombok.NoArgsConstructor;

public class ProductUtil {
    public Product productRequestToProduct(ProductRequest productRequest) {
        return new Product(productRequest.getId(), productRequest.getName(), productRequest.getDescription(), productRequest.getPrice());
    }

    public ProductResponse productToProductResponse(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }
}
