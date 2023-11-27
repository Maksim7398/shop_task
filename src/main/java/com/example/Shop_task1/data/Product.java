package com.example.Shop_task1.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.UUID;

import static java.time.format.FormatStyle.LONG;

@Entity
@Table(name = "product")
@Data
public class Product {
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
    @JsonFormat(locale = "ru",pattern = "dd MMMM yyyy")
    private LocalDate exchangeCount;
    @CreationTimestamp
    @Column(name = "create_date")
    private LocalDate createDate;

}
