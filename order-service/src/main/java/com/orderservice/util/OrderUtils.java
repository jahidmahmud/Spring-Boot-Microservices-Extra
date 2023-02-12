package com.orderservice.util;

import com.orderservice.dto.OrderLineItemsDto;
import com.orderservice.dto.OrderRequest;
import com.orderservice.model.Order;
import com.orderservice.model.OrderLineItems;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
public class OrderUtils {
    public Order orderRequestToOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList().stream()
                .map(this::orderLineItemsDtoToOrderLineItems)
                .collect(Collectors.toList());
        order.setOrderLineItemsList(orderLineItems);
        return order;
    }

    public OrderLineItems orderLineItemsDtoToOrderLineItems(OrderLineItemsDto orderLineItemsDto) {
        return new OrderLineItems(orderLineItemsDto.getId(), orderLineItemsDto.getSkuCode(), orderLineItemsDto.getPrice(), orderLineItemsDto.getQuantity());
    }
}
