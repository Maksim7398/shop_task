package com.example.shop.event.service;

import com.example.shop.controller.event.controller.events.EventSource;

public interface OrderEventService {
     String processEvent(EventSource eventSource);
}
