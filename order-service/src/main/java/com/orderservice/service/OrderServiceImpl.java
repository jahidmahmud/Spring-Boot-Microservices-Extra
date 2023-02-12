package com.orderservice.service;

import com.orderservice.dto.InventoryResponse;
import com.orderservice.dto.OrderRequest;
import com.orderservice.event.OrderPlacedEvent;
import com.orderservice.model.Order;
import com.orderservice.model.OrderLineItems;
import com.orderservice.repository.OrderRepository;
import com.orderservice.util.OrderUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    private WebClient.Builder webClientBuilder;
    OrderUtils utils = new OrderUtils();

    private final KafkaTemplate<String,OrderPlacedEvent> kafkaTemplate;

    @Override
    public void placeOrder(OrderRequest orderRequest) {
        try {
            Order order=utils.orderRequestToOrder(orderRequest);
            List<String> skuCodes=order.getOrderLineItemsList().stream()
                    .map(OrderLineItems::getSkuCode)
                    .toList();
            InventoryResponse[] inventoryResponses=webClientBuilder.build().get()
                    .uri("http://inventory-service/api/inventory",uriBuilder -> uriBuilder
                            .queryParam("skuCode",skuCodes).build())
                    .retrieve()
                    .bodyToMono(InventoryResponse [].class)
                    .block();
            Boolean status=Arrays.stream(inventoryResponses).allMatch(InventoryResponse::isInStock);
            if (status){
                orderRepository.save(order);
                kafkaTemplate.send("notificationTopic",new OrderPlacedEvent(order.getOrderNumber()));
            } else {
                log.info("Failed to place order");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
