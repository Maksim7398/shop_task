package com.example.shop.controller.event.controller;

import com.example.shop.controller.event.controller.events.HttpEvent;
import com.example.shop.event.service.OrderEventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrdersEventController {

    private final OrderEventService orderEventService;

    @PostMapping("/process/events")
    public String processEvent(@RequestBody @Valid HttpEvent httpEvent) {
        return orderEventService.processEvent(httpEvent);
    }
}
