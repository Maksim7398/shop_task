package com.example.shop.event.service.config;

import com.example.shop.controller.event.controller.events.EventSource;
import com.example.shop.event.service.handlers.EventHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;


@Configuration
public class EventHandlerConfig {
    @Bean
    <T extends EventSource> Set<EventHandler<T>> eventHandlers(Set<EventHandler<T>> eventHandlers) {
        return new HashSet<>(eventHandlers);
    }
}
