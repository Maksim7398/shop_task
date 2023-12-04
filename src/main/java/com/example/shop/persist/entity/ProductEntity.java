package com.example.shop.persist.entity;

import com.example.shop.model.Category;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
<<<<<<< HEAD
import jakarta.validation.constraints.NotBlank;
=======
>>>>>>> develop
import jakarta.validation.constraints.NotNull;
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
<<<<<<< HEAD
    @Column(name = "article")
    private String article;
=======
>>>>>>> develop
    @Column(name = "title",nullable = false)
    private String title;
    @Column(name = "description")
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
