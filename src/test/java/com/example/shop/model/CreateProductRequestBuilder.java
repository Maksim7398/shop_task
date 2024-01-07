package com.example.shop.model;

import com.example.shop.controller.product.request.CreateProductRequest;

import java.math.BigDecimal;

import static com.example.shop.model.Category.BREAD;

public class CreateProductRequestBuilder {
    public static final String DEFAULT_ARTICLE = "1";
    public static final String DEFAULT_TITLE = "Bread";
    public static final String DEFAULT_DESCRIPTION = "is bread";
    public static final Category DEFAULT_CATEGORY = BREAD;
    public static final BigDecimal DEFAULT_PRICE = new BigDecimal(100);
    public static final Integer DEFAULT_QUANTITY = 5;

    private String article = DEFAULT_ARTICLE;
    private String title = DEFAULT_TITLE;
    private String description = DEFAULT_DESCRIPTION;
    private Category category = DEFAULT_CATEGORY;
    private BigDecimal price = DEFAULT_PRICE;
    private Integer quantity = DEFAULT_QUANTITY;

    private CreateProductRequestBuilder() {
    }

    public static CreateProductRequestBuilder aCreateProductRequest() {
        return new CreateProductRequestBuilder();
    }


    public CreateProductRequestBuilder withArticle(String article) {
        this.article = article;
        return this;
    }

    public CreateProductRequestBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public CreateProductRequestBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public CreateProductRequestBuilder withCategories(Category category) {
        this.category = category;
        return this;
    }

    public CreateProductRequestBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public CreateProductRequestBuilder withQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public CreateProductRequest build() {
        return CreateProductRequest.builder()
                .article(article)
                .title(title)
                .description(description)
                .category(category)
                .price(price)
                .quantity(quantity)
                .build();
    }

}