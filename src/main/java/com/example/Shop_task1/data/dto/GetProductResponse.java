package com.example.Shop_task1.data.dto;


import com.example.Shop_task1.data.Categories;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;


import java.time.LocalDate;
import java.util.UUID;

@Data
public class GetProductResponse {

    private UUID id;

    private String title;

    private String description;

    private Categories categories;

    private Double price;

    private int count;
    @JsonFormat(locale = "ru",pattern = "dd MMMM yyyy")
   private LocalDate exchangeCount;

    private LocalDate createDate;
}
