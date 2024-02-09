package com.example.shop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrder {

    private  UUID id;
    private  String article;
    private  String title;
    private  String description;
    private  Category category;
    private  BigDecimal price;
    private Integer quantity;

}
