package com.example.shop.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.example.shop.model.Category.BREAD;
public class ProductDtoBuilder {
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
    private LocalDateTime lastQuantityChange = DEFAULT_LAST_QUENTITY_CHANGED;
    private LocalDateTime createDate = DEFAULT_CREATE_DATE;
    private Boolean isAvailable = IS_AVAILABLE;

    private ProductDtoBuilder() {
    }
    public static ProductDtoBuilder aProductDto() {
       return new ProductDtoBuilder();
    }
    public ProductDtoBuilder withId(UUID id) {
        this.id = id;
        return this;
    }
    public ProductDtoBuilder withLastQuantityChange(LocalDateTime lastQuantityChange) {
        this.lastQuantityChange = lastQuantityChange;
        return this;
    }
    public ProductDtoBuilder withCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
        return this;
    }
    public ProductDtoBuilder withArticle(String article) {
        this.article = article;
        return this;
    }
    public ProductDtoBuilder withTitle(String title) {
        this.title = title;
        return this;
    }
    public ProductDtoBuilder withDescription(String description) {
        this.description = description;
        return this;
    }
    public ProductDtoBuilder withCategories(Category category) {
        this.category = category;
        return this;
    }
    public ProductDtoBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
    public ProductDtoBuilder withQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }
    public ProductDtoBuilder withIsAvailable(Boolean available) {
        this.isAvailable = available;
        return this;
    }
    public ProductDto build() {
        return ProductDto.builder()
                .id(id)
                .article(article)
                .title(title)
                .description(description)
                .category(category)
                .price(price)
                .quantity(quantity)
                .lastQuantityChange(lastQuantityChange)
                .createDate(createDate)
                .isAvailable(isAvailable)
                .build();
    }
}
