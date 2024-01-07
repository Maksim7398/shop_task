package com.example.shop.controller.product.response;

import com.example.shop.model.Category;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class UpdateProductResponse {
    private final UUID id;
    private final String article;
    private final String title;
    private final String description;
    private final Category category;
    private final BigDecimal price;
    private Integer quantity;
    @JsonFormat(locale = "ru", pattern = "dd MMMM yyyy")
    private final LocalDateTime lastQuantityChange;
    private final LocalDateTime createDate;
    private Boolean isAvailable;
}
