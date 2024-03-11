package com.example.shop.event.service.handlers;

import com.example.shop.controller.event.controller.events.Event;
import com.example.shop.controller.event.controller.events.EventSource;
import com.example.shop.controller.event.controller.request.OrderCreateEventData;
import com.example.shop.controller.request.CreateOrderRequest;
import com.example.shop.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderCreateHandler implements EventHandler<OrderCreateEventData> {

    private final OrderService orderService;

    @Override
    public boolean canHandle(EventSource eventSource) {
        return Event.CREATE_ORDER.equals(eventSource.getEvent());
    }

    @Override
    public String handleEvent(OrderCreateEventData eventSource) {
        final UUID save = orderService
            .save(CreateOrderRequest.builder().products(eventSource.getProducts()).build(),eventSource.getUserID());

        return save.toString();
    }
}
