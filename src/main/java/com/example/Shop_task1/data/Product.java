package com.example.Shop_task1.data;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private String title;

    private String description;

    private Categories categories;

    private Double price;

    private int count;

    private Date exchangeCount;

    private Date createDate;


}
