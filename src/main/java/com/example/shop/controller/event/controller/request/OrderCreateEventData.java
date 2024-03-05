package com.example.shop.controller.event.controller.request;

import com.example.shop.controller.request.CreateOrderRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Data
@Builder
@Validated
public class OrderCreateEventData implements HttpEvent {

    private  CreateOrderRequest createOrderRequest;

    private  UUID userId;

    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public Event getEvent() {
        return Event.CREATE_ORDER;
    }
}
