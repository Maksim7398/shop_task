package com.example.shop.controller.event.controller.request;

import com.example.shop.controller.event.controller.events.Event;
import com.example.shop.controller.event.controller.events.HttpEvent;
import com.example.shop.controller.request.CreateOrderedProduct;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@Validated
public class OrderAddProductsEventData implements HttpEvent {

    @NotNull
    private final UUID orderId;

    @NotEmpty
    private final List<CreateOrderedProduct> products;

    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public Event getEvent() {
        return Event.ADD_PRODUCTS_IN_ORDER;
    }
}
