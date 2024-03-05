package com.example.shop.event.service.handle;

import com.example.shop.controller.event.controller.request.Event;
import com.example.shop.controller.event.controller.request.EventSource;
import com.example.shop.controller.event.controller.request.OrderUpdateStatusEventData;
import com.example.shop.model.Status;
import com.example.shop.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

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
        return orderService.updateStatus(UUID.fromString("c284a37f-bc06-43fa-a1d9-d50ce033ea11"), Status.REJECTED);
    }
}
