package com.example.Shop_task1.data;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    private String description;

    private Categories categories;

    private Double price;

    private int count;
    @Column(name = "exchange_count")
    private LocalDate exchangeCount;
    @Column(name = "create_date")
    private LocalDate createDate;


}
