package com.example.Shop_task1.data;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProductDto {

    private String title;

    private String description;

    private Categories categories;

    private Double price;

    private int count;

    private LocalDate exchangeCount;

    private LocalDate createDate;


}
