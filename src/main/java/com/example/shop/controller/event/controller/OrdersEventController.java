package com.example.shop.controller.event.controller;

import com.example.shop.controller.event.controller.events.HttpEvent;
import com.example.shop.event.service.OrderEventService;
import com.example.shop.kafka.Producer;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrdersEventController {

    private final Producer producer;

    private final OrderEventService orderEventService;

    @PostMapping("/process/events")
    public String processEvent(@RequestBody @Valid HttpEvent httpEvent) throws JsonProcessingException {
        producer.sendEvent(httpEvent);
        return orderEventService.processEvent(httpEvent);
    }
}
