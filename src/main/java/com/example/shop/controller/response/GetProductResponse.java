package com.example.shop.controller.response;


import com.example.shop.model.Categories;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class GetProductResponse {

    private final UUID id;

    private final String title;

    private final String description;

    private final Categories categories;

    private final Double price;

    private Integer quantity;
    @JsonFormat(locale = "ru", pattern = "dd MMMM yyyy")
    private final LocalDateTime lastQuantityChange;

    private final LocalDateTime createDate;
}
