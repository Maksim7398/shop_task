package com.example.Shop_task1.data;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "product")
@Data
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotEmpty
    private String title;
    @NotEmpty
    private String description;
    @Enumerated(value = EnumType.STRING)
    private Categories categories;

    private Double price;
    @Digits(integer = 5,fraction = 0)
    private int count;
    @UpdateTimestamp
    @Column(name = "exchange_count")
    private LocalDate exchangeCount;
    @CreationTimestamp
    @Column(name = "create_date")
    private LocalDate createDate;

}
