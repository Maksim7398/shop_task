package com.example.shop.controller.event.controller.events;

import com.example.shop.controller.event.controller.request.OrderAddProductsEventData;
import com.example.shop.controller.event.controller.request.OrderCreateEventData;
import com.example.shop.controller.event.controller.request.OrderDeleteEventData;
import com.example.shop.controller.event.controller.request.OrderUpdateStatusEventData;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "event"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = OrderCreateEventData.class, name = "CREATE_ORDER"),
        @JsonSubTypes.Type(value = OrderUpdateStatusEventData.class, name = "UPDATE_STATUS_ORDER"),
        @JsonSubTypes.Type(value = OrderAddProductsEventData.class, name = "ADD_PRODUCTS_IN_ORDER"),
        @JsonSubTypes.Type(value = OrderDeleteEventData.class, name = "DELETE_ORDER")
})
public interface HttpEvent extends EventSource {
}
