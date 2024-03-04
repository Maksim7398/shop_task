package com.example.shop.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;
@Data
@Builder
@AllArgsConstructor
public class CreateOrderedProduct {
    @NotNull
    private final UUID id;
    @NotBlank(message = "quantity must not be blank")
    private final Integer quantity;
}
