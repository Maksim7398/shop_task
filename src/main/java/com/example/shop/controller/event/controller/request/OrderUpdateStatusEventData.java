package com.example.shop.controller.event.controller.request;

import com.example.shop.model.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Data
@Builder
@Validated
public class OrderUpdateStatusEventData implements HttpEvent {

    private final Status status;

    private final UUID orderId;

    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public Event getEvent() {
        return Event.UPDATE_STATUS_ORDER;
    }
}
