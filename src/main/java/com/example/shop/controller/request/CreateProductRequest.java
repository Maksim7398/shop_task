package com.example.shop.controller.request;

import com.example.shop.model.Categories;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CreateProductRequest {
    @NotBlank(message = "title must not be blank")
    private String title;
    @NotBlank(message = "description must not be blank")
    private String description;
    @NotBlank(message = "categories must not be blank")
    private Categories categories;
    @Positive(message = "Price should be at least 0 or higher")
    private Double price;
    @NotBlank(message = "quantity must not be blank")
    private Integer quantity;

}
