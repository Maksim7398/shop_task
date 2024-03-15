package com.example.shop.controller.event.controller;

import com.example.shop.controller.event.controller.events.HttpEvent;
import com.example.shop.event.service.OrderEventService;
import com.example.shop.kafka.Producer;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrdersEventController {

    private final Producer producer;

    private final OrderEventService orderEventService;

    @Value("${spring.kafka.topic}")
    private String ORDER_TOPIC;

    @PostMapping("/orders/events")
    public String ordersEvent(@RequestBody @Valid HttpEvent httpEvent){
        return orderEventService.processEvent(httpEvent);
    }

    @SneakyThrows
    @PostMapping("/kafka/events")
    public void kafkaEventTest(@RequestParam(required = false) String key, @RequestBody @Valid HttpEvent httpEvent) {
        producer.sendEvent(key,"order_topic2",httpEvent.getEvent().name());
        producer.sendHttpEvent(key,ORDER_TOPIC, httpEvent);
    }
}
