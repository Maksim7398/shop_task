package com.example.Shop_task1.data.dto;

import com.example.Shop_task1.data.Categories;
import lombok.Builder;
import lombok.Data;

@Data
public class CreateProductRequest {

    private String title;

    private String description;

    private Categories categories;

    private Double price;

    private int count;


}
