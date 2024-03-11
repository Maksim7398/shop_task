package com.example.shop.kafka;

import com.example.shop.controller.event.controller.events.HttpEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "app", name = "kafka.enabled", matchIfMissing = false)
public class Producer {

    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    public static final String ORDER_TOPIC = "order_topic";

    public void sendEvent(HttpEvent httpEvent) throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        byte[] data = objectMapper.writeValueAsBytes(httpEvent);

        kafkaTemplate.send(ORDER_TOPIC, data);
    }
}
