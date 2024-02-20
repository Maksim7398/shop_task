package com.example.shop.model;

import com.example.shop.persist.entity.CompositeKey;
import com.example.shop.persist.entity.OrderedProductEntity;

import java.math.BigDecimal;

public class OrderedProductsEntityBuilder {
    public static final CompositeKey DEFAULT_COMPOSITE_KEY = new CompositeKey(OrderBuilder.DEFAULT_ID,ProductEntityBuilder.DEFAULT_ID);
    public static final BigDecimal DEFAULT_PRICE = new BigDecimal(100);
    public static final Integer DEFAULT_QUANTITY = 5;

    private CompositeKey compositeKey = DEFAULT_COMPOSITE_KEY;
    private BigDecimal price = DEFAULT_PRICE;
    private Integer quantity = DEFAULT_QUANTITY;

    private OrderedProductsEntityBuilder() {
    }

    public static OrderedProductsEntityBuilder aOrderedProductEntity() {
        return new OrderedProductsEntityBuilder();
    }

    public OrderedProductsEntityBuilder withCompositeKey(CompositeKey compositeKey) {
        this.compositeKey = compositeKey;
        return this;
    }

    public OrderedProductsEntityBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public OrderedProductsEntityBuilder withQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public OrderedProductEntity build() {
        return OrderedProductEntity.builder()
                .compositeKey(compositeKey)
                .price(price)
                .quantity(quantity)
                .build();
    }
}
