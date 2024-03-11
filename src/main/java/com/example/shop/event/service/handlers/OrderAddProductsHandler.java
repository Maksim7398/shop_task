package com.example.shop.event.service.handlers;

import com.example.shop.controller.event.controller.events.Event;
import com.example.shop.controller.event.controller.events.EventSource;
import com.example.shop.controller.event.controller.request.OrderAddProductsEventData;
import com.example.shop.controller.request.UpdateOrderRequest;
import com.example.shop.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
        return String.valueOf(orderService.addProductInOrderExists(eventSource.getOrderId(),
                UpdateOrderRequest.builder().products(eventSource.getProducts()).build()));
    }
}
