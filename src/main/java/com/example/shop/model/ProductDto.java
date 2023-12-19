package com.example.shop.model;

import lombok.Builder;
import lombok.Data;

import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
@Data
@Builder
public class ProductDto {

    private final UUID id;
    @Nullable
    private final String article;
    @Nullable
    private final String title;
    @Nullable
    private final String description;
    @Nullable
    private final Category category;
    private final BigDecimal price;
    private final Integer quantity;
    private final LocalDateTime lastQuantityChange;
    private final LocalDateTime createDate;
    private final Boolean isAvailable;
}
