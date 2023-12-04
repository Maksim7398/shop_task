package com.example.shop.controller.request;

import com.example.shop.model.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class UpdateProductRequest {
    @NotBlank(message = "article must not be blank")
    private String article;
    @NotBlank(message = "title must not be blank")
    private String title;
    @NotBlank(message = "description must not be blank")
    private String description;
    @NotNull(message = "categories must not be blank")
    private Category category;
    @Positive(message = "Price should be at least 0 or higher")
    private BigDecimal price;
    @NotNull(message = "quantity must not be blank")
    private Integer quantity;
}
