package com.example.shop.controller.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class CreateOrderRequest {
    @NotEmpty
    private List<CreateOrderedProduct> products;
}
