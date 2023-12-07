package com.example.shop.service.request;

import com.example.shop.model.Category;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class ImmutableUpdateProductRequest {

    private String article;

    private String title;

    private String description;

    private Category category;

    private BigDecimal price;

    private Integer quantity;
}
