package com.example.shop.event.service.handle;

import com.example.shop.controller.event.controller.request.EventSource;

public interface EventHandler< T extends EventSource> {

    boolean canHandle(EventSource eventSource);

    String handleEvent(T eventSource);
}
