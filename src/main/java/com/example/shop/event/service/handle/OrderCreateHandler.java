package com.example.shop.event.service.handle;

import com.example.shop.controller.event.controller.request.Event;
import com.example.shop.controller.event.controller.request.EventSource;
import com.example.shop.controller.event.controller.request.OrderCreateEventData;
import com.example.shop.controller.request.CreateOrderRequest;
import com.example.shop.controller.request.CreateOrderedProduct;
import com.example.shop.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
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
                .save(CreateOrderRequest.builder().products(List.of(
                        CreateOrderedProduct.builder()
                                .id(UUID.fromString("4b30bf06-bf20-49b7-8621-bbf281149517"))
                                .quantity(5)
                                .build())).build(),
                        UUID.fromString("ad278799-dc7a-4ecf-8940-de621d2b7371"));

        return save.toString();
    }
}
