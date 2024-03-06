package com.example.shop.event.service.handlers;

import com.example.shop.controller.event.controller.events.Event;
import com.example.shop.controller.event.controller.events.EventSource;
import com.example.shop.controller.event.controller.request.OrderUpdateStatusEventData;
import com.example.shop.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderUpdateStatusHandler implements EventHandler<OrderUpdateStatusEventData> {

    private final OrderService orderService;

    @Override
    public boolean canHandle(EventSource eventSource) {
        return Event.UPDATE_STATUS_ORDER.equals(eventSource.getEvent());
    }

    @Override
    public String handleEvent(OrderUpdateStatusEventData eventSource) {
        return orderService.updateStatus(eventSource.getOrderId(),eventSource.getStatus());
    }
}
