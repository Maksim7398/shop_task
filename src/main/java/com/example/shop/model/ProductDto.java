package com.example.shop.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ProductDto {

    private final UUID id;

    private final String title;

    private final String description;

    private final Categories categories;

    private final Double price;

    private Integer quantity;

    private final LocalDateTime lastQuantityChange;

    private final LocalDateTime createDate;
}
