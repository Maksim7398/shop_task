package com.example.shop.event.service;

import com.example.shop.controller.event.controller.events.EventSource;
import com.example.shop.event.service.handlers.EventHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProcessServiceImpl implements ProcessService {

    private final Set<EventHandler<EventSource>> eventHandlers;

    @Override
    public String processEvent(final EventSource eventSource) {
        return eventHandlers.stream()
                .filter(eventSourceEventHandler -> eventSourceEventHandler.canHandle(eventSource))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Handler for eventsource not found"))
                .handleEvent(eventSource);
    }
}
