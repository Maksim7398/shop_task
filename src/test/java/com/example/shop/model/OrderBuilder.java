package com.example.shop.model;

import com.example.shop.persist.entity.OrderEntity;
import com.example.shop.persist.entity.OrderedProductEntity;
import com.example.shop.persist.entity.UserEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class OrderBuilder {

    public static final UUID DEFAULT_ID = UUID.randomUUID();

    public static final Status DEFAULT_STATUS = Status.CREATED;

    public static final UserEntity DEFAULT_USER = UserBuilder.aUserEntity().build();

    public static final List<OrderedProductEntity> DEFAULT_ORDERED_PRODUCT = List.of(OrderedProductEntity.builder().build());

    public static final BigDecimal DEFAULT_ORDER_PRICE = new BigDecimal(100);
    public static final LocalDateTime DEFAULT_CREATE_DATE = LocalDateTime.now();

    private UUID id = DEFAULT_ID;
    private Status status = DEFAULT_STATUS;
    private UserEntity userEntity = DEFAULT_USER;
    private List<OrderedProductEntity> orderedProduct = DEFAULT_ORDERED_PRODUCT;
    private BigDecimal price = DEFAULT_ORDER_PRICE;
    private LocalDateTime createDate = DEFAULT_CREATE_DATE;

    private OrderBuilder() {
    }

    public static OrderBuilder aOrderEntity() {
        return new OrderBuilder();
    }

    public OrderBuilder withID(UUID id) {
        this.id = id;
        return this;
    }

    public OrderBuilder withStatus(Status status) {
        this.status = status;
        return this;
    }

    public OrderBuilder withUser(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }

    public OrderBuilder withOrderedProduct(List<OrderedProductEntity> orderedProduct) {
        this.orderedProduct = orderedProduct;
        return this;
    }

    public OrderBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public OrderBuilder withCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public OrderEntity build() {
        return OrderEntity.builder()
                .id(id)
                .status(status)
                .user(userEntity)
                .orderedProducts(orderedProduct)
                .orderPrice(price)
                .createDate(createDate)
                .build();
    }
}
