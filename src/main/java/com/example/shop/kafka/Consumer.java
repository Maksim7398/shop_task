package com.example.shop.kafka;

import com.example.shop.controller.event.controller.events.EventSource;
import com.example.shop.controller.event.controller.events.HttpEvent;
import com.example.shop.event.service.handlers.EventHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "app", name = "kafka.enabled", matchIfMissing = false)
public class Consumer {

    private final Set<EventHandler<EventSource>> eventHandlers;

    @KafkaListener(topics = "order_topic", containerFactory = "kafkaListenerContainerFactoryString")
    public void listen(byte[] message) {
        final ObjectMapper objectMapper = new ObjectMapper();

        try {
            final HttpEvent eventSource = objectMapper.readValue(message, HttpEvent.class);
            log.info("EventSource: {}", eventSource);

            eventHandlers.stream()
                    .filter(eventSourceEventHandler -> eventSourceEventHandler.canHandle(eventSource))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Handler for eventsource not found"))
                    .handleEvent(eventSource);

            log.info("MESSAGE READING");
        } catch (IOException e) {
            log.error("Couldn't parse message: {}; exception: ", message, e);
        }
    }
}
