package com.example.shop.controller.request;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class SearchFilter {
    private String title;
    private Integer quantity;
    private BigDecimal price;
    private Boolean isAvailable;
}
