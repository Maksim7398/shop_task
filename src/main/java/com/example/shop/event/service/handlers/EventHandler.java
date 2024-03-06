package com.example.shop.event.service.handlers;

import com.example.shop.controller.event.controller.events.EventSource;

public interface EventHandler< T extends EventSource> {

    boolean canHandle(EventSource eventSource);

    String handleEvent(T eventSource);
}
