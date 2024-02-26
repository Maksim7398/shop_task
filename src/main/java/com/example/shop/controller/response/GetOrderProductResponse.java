package com.example.shop.controller.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;
@Data
@Builder
public class GetOrderProductResponse {

    private UUID productId;

    private String description;

    private Integer quantity;

    private BigDecimal price;
}
