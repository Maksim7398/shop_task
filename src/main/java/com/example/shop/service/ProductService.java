package com.example.shop.service;

import com.example.shop.controller.request.CreateProductRequest;
import com.example.shop.model.ProductDto;
import com.example.shop.service.request.ImmutableUpdateProductRequest;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<ProductDto> productList();

    UUID save(CreateProductRequest request);

    ProductDto getProductById(UUID id);

    void deleteProductById(UUID id);

    ProductDto updateProduct(final UUID id, final ImmutableUpdateProductRequest request);
}
