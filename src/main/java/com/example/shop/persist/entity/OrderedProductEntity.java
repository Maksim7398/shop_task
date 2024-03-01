package com.example.shop.persist.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Table(name = "ordered_product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class  OrderedProductEntity{

    @EmbeddedId
    private CompositeKey compositeKey;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private BigDecimal price;
}
