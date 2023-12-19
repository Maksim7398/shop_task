package com.example.shop.model;

import com.example.shop.persist.entity.ProductEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.example.shop.model.Category.BREAD;

public class ProductEntityBuilder {
    public static final UUID DEFAULT_ID = UUID.randomUUID();
    public static final String DEFAULT_ARTICLE = "1";
    public static final String DEFAULT_TITLE = "Bread";
    public static final String DEFAULT_DESCRIPTION = "is bread";
    public static final Category DEFAULT_CATEGORY = BREAD;
    public static final BigDecimal DEFAULT_PRICE = new BigDecimal(100);
    public static final Integer DEFAULT_QUANTITY = 5;
    public static final LocalDateTime DEFAULT_LAST_QUENTITY_CHANGED = LocalDateTime.now();
    public static final LocalDateTime DEFAULT_CREATE_DATE = LocalDateTime.now();
    public static final Boolean IS_AVAILABLE = false;


    private UUID id = DEFAULT_ID;
    private String article = DEFAULT_ARTICLE;
    private String title = DEFAULT_TITLE;
    private String description = DEFAULT_DESCRIPTION;
    private Category category = DEFAULT_CATEGORY;
    private BigDecimal price = DEFAULT_PRICE;
    private Integer quantity = DEFAULT_QUANTITY;
    private LocalDateTime lastQuantityChanged = DEFAULT_LAST_QUENTITY_CHANGED;
    private LocalDateTime createDate = DEFAULT_CREATE_DATE;
    private Boolean isAvailable = IS_AVAILABLE;
    private ProductEntityBuilder() {
    }
    public static ProductEntityBuilder aProductEntity() {
        return new ProductEntityBuilder();
    }
    public ProductEntityBuilder withId(UUID id) {
        this.id = id;
        return this;
    }
    public ProductEntityBuilder withLastQuantityChange(LocalDateTime lastQuantityChange) {
        this.lastQuantityChanged = lastQuantityChange;
        return this;
    }
    public ProductEntityBuilder withCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
        return this;
    }
    public ProductEntityBuilder withArticle(String article) {
        this.article = article;
        return this;
    }
    public ProductEntityBuilder withTitle(String title) {
        this.title = title;
        return this;
    }
    public ProductEntityBuilder withDescription(String description) {
        this.description = description;
        return this;
    }
    public ProductEntityBuilder withCategories(Category category) {
        this.category = category;
        return this;
    }
    public ProductEntityBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
    public ProductEntityBuilder withQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }
    public ProductEntityBuilder withIsAvailable(Boolean available) {
        this.isAvailable = available;
        return this;
    }
    public ProductEntity build() {
        return ProductEntity.builder()
                .id(id)
                .article(article)
                .title(title)
                .description(description)
                .category(category)
                .price(price)
                .quantity(quantity)
                .lastQuantityChange(lastQuantityChanged)
                .createDate(createDate)
                .isAvailable(isAvailable)
                .build();
    }
}
