package com.example.shop.controller.request;

import com.example.shop.model.Status;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class CreateOrderRequest {
    @NotNull
    private List<CreateOrder> product;
    @NotNull(message = "status must not be null")
    private Status status;
    @NotNull
    private UUID user;
}
