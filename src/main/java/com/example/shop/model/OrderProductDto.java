package com.example.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;
@Data
@AllArgsConstructor
public class OrderProductDto {
    private UUID productId;

    private String description;

    private Integer quantity;

    private BigDecimal price;
}
