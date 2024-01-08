package com.example.shop.controller.response;

import com.example.shop.model.Category;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class GetProductResponse {
    private UUID id;
    private String article;
    private String title;
    private String description;
    private Category category;
    private BigDecimal price;
    private Integer quantity;
    @JsonFormat(locale = "ru", pattern = "dd MMMM yyyy")
    private LocalDateTime lastQuantityChange;
    private LocalDateTime createDate;
    private Boolean isAvailable;
}
