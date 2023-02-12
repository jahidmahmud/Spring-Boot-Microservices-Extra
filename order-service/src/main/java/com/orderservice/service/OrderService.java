package com.orderservice.service;

import com.orderservice.dto.OrderRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface OrderService {
    void placeOrder(OrderRequest orderRequest);
}
