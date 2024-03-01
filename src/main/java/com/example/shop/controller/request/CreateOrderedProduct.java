package com.example.shop.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;
@Data
@Builder
@AllArgsConstructor
public class CreateOrderedProduct {

    private final UUID id;

    private final Integer quantity;
}
