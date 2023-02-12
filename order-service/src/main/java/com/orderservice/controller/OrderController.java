package com.orderservice.controller;

import com.orderservice.dto.OrderRequest;
import com.orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    Logger log = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    OrderService service;

    @PostMapping("/")
//    @CircuitBreaker(name = "inventory",fallbackMethod = "fallbackMethod")
//    @TimeLimiter(name = "inventory",fallbackMethod = "fallbackMethod")
//    @Retry(name = "inventory",fallbackMethod = "fallbackMethod")
    //public ResponseEntity<CompletableFuture<String>> placeOrder(@RequestBody OrderRequest orderRequest) {
    public ResponseEntity<String> placeOrder(@RequestBody OrderRequest orderRequest) {
        try {
            log.info("Order-service");
            service.placeOrder(orderRequest);
            return ResponseEntity.status(HttpStatus.CREATED)
                    //.body(CompletableFuture.supplyAsync(()->"Order Placed Successfully"));
                    .body("Order Placed Successfully");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public CompletableFuture<String> fallbackMethod(OrderRequest orderRequest,RuntimeException runtimeException){
        return CompletableFuture.supplyAsync(()->"Something went wrong");
    }
}
