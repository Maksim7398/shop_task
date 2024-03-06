package com.example.shop.event.service.handlers;

import com.example.shop.controller.event.controller.events.Event;
import com.example.shop.controller.event.controller.events.EventSource;
import com.example.shop.controller.event.controller.request.OrderDeleteEventData;
import com.example.shop.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderDeleteHandler implements EventHandler<OrderDeleteEventData> {

    private final OrderService orderService;

    @Override
    public boolean canHandle(EventSource eventSource) {
        return Event.DELETE_ORDER.equals(eventSource.getEvent());
    }

    @Override
    public String handleEvent(OrderDeleteEventData eventSource) {
        return String.valueOf(orderService.deleteOrder(UUID.fromString("6f7e02b2-8544-48dc-97b2-41349744a0d2")));
    }
}
