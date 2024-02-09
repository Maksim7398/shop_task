package com.example.shop.persist.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderToProduct {

    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private CompositeKey orderId;


    @Column(name = "quantity")
    private Integer quantity;
}
