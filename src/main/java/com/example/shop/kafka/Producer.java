package com.example.shop.kafka;

import com.example.shop.controller.event.controller.events.HttpEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "app", name = "kafka.enabled", matchIfMissing = false)
public class Producer {

    private final KafkaTemplate<String, byte[]> kafkaTemplateByteArray;

    private final KafkaTemplate<String, String> kafkaTemplateString;


    public void sendHttpEvent(final String key,final String topic, final HttpEvent httpEvent) throws JsonProcessingException, ExecutionException, InterruptedException {
        final ObjectMapper objectMapper = new ObjectMapper();
        byte[] data = objectMapper.writeValueAsBytes(httpEvent);
        final CompletableFuture<SendResult<String, byte[]>> send = kafkaTemplateByteArray.send(topic, key, data);

        log.info("KEY: {}", key);
        log.info("TOPIC: {}", send.get().getRecordMetadata().topic());
    }

    public void sendEvent(final String key,final String topic, final String message) throws JsonProcessingException, ExecutionException, InterruptedException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final String data = objectMapper.writeValueAsString(message);
        final CompletableFuture<SendResult<String, String>> send = kafkaTemplateString.send(topic,key,data);

        log.info("KEY: {}", key);
        log.info("TOPIC: {}", send.get().getRecordMetadata().topic());
    }
}
