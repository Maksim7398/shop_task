package com.example.shop.event.service.handlers;

import com.example.shop.controller.event.controller.events.Event;
import com.example.shop.controller.event.controller.events.EventSource;
import com.example.shop.controller.event.controller.request.OrderAddProductsEventData;
import com.example.shop.controller.request.CreateOrderedProduct;
import com.example.shop.controller.request.UpdateOrderRequest;
import com.example.shop.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderAddProductsHandler implements EventHandler<OrderAddProductsEventData> {

    private final OrderService orderService;

    @Override
    public boolean canHandle(EventSource eventSource) {
        return Event.ADD_PRODUCTS_IN_ORDER.equals(eventSource.getEvent());
    }

    @Override
    public String handleEvent(OrderAddProductsEventData eventSource) {
        return String.valueOf(orderService.addProductInOrderExists(UUID.fromString("6f7e02b2-8544-48dc-97b2-41349744a0d2"),
                UpdateOrderRequest.builder()
                        .products(
                                List.of(CreateOrderedProduct.builder()
                                .id(UUID.fromString("a68b4fc5-a5ab-4a21-9b92-9537450f2c0c"))
                                .quantity(5).build())
                        ).build()));
    }
}
