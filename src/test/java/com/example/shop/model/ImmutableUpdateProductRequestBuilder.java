package com.example.shop.model;


import com.example.shop.service.product.request.ImmutableUpdateProductRequest;

import java.math.BigDecimal;

import static com.example.shop.model.Category.MEAT;

public class ImmutableUpdateProductRequestBuilder {
    public static final String DEFAULT_ARTICLE = "2";
    public static final String DEFAULT_TITLE = "Sausage";
    public static final String DEFAULT_DESCRIPTION = "is sausage";
    public static final Category DEFAULT_CATEGORY = MEAT;
    public static final BigDecimal DEFAULT_PRICE = new BigDecimal(200);
    public static final Integer DEFAULT_QUANTITY = 10;
    public static final Boolean IS_AVAILABLE = false;
    private String article = DEFAULT_ARTICLE;
    private String title = DEFAULT_TITLE;
    private String description = DEFAULT_DESCRIPTION;
    private Category category = DEFAULT_CATEGORY;
    private BigDecimal price = DEFAULT_PRICE;
    private Integer quantity = DEFAULT_QUANTITY;
    private Boolean isAvailable = IS_AVAILABLE;
    private ImmutableUpdateProductRequestBuilder() {
    }
    public static ImmutableUpdateProductRequestBuilder aImmutableUpdateProductRequest() {
        return new ImmutableUpdateProductRequestBuilder();

    }
    public ImmutableUpdateProductRequestBuilder withArticle(String article) {
        this.article = article;
        return this;
    }
    public ImmutableUpdateProductRequestBuilder withTitle(String title) {
        this.title = title;
        return this;
    }
    public ImmutableUpdateProductRequestBuilder withDescription(String description) {
        this.description = description;
        return this;
    }
    public ImmutableUpdateProductRequestBuilder withCategories(Category category) {
        this.category = category;
        return this;
    }
    public ImmutableUpdateProductRequestBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
    public ImmutableUpdateProductRequestBuilder withQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }
    public ImmutableUpdateProductRequestBuilder withIsAvailable(Boolean available) {
        this.isAvailable = available;
        return this;
    }
    public ImmutableUpdateProductRequest build() {
        return ImmutableUpdateProductRequest.builder()

                .article(article)
                .title(title)
                .description(description)
                .category(category)
                .price(price)
                .quantity(quantity)
                .isAvailable(isAvailable)
                .build();
    }
}
