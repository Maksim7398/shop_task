package com.example.shop.persist.entity;

import com.example.shop.model.Categories;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "product")
@Data
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "title",nullable = false)
    private String title;
    @Column(name = "description",nullable = false)
    private String description;
    @Enumerated(value = EnumType.STRING)
    private Categories categories;
    @Column(name = "price",nullable = false)
    private Double price;
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "last_quantity_change")
    private LocalDateTime lastQuantityChange;
    @CreationTimestamp
    @Column(name = "create_date")
    private LocalDateTime createDate;

}
