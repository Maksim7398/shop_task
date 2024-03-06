package com.example.shop.controller.event.controller.request;

import com.example.shop.controller.event.controller.events.Event;
import com.example.shop.controller.event.controller.events.HttpEvent;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Builder
@Validated
public class OrderDeleteEventData implements HttpEvent {
    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public Event getEvent() {
        return Event.DELETE_ORDER;
    }
}
