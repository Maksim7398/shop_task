package com.example.shop.controller.event.controller.request;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "event"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = OrderCreateEventData.class, name = "CREATE_ORDER"),
        @JsonSubTypes.Type(value = OrderUpdateStatusEventData.class, name = "UPDATE_STATUS_ORDER")
})
public interface HttpEvent extends EventSource {
}
