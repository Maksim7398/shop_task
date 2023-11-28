package com.example.shop.persist.entity;

import com.example.shop.model.Category;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
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
    private Category category;
    @Column(name = "price",nullable = false)
    private BigDecimal price;
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    @Column(name = "last_quantity_change")
    private LocalDateTime lastQuantityChange;
    @CreationTimestamp
    @Column(name = "create_date")
    private LocalDateTime createDate;

}
