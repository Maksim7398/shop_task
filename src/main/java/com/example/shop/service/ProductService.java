package com.example.shop.service;

import com.example.shop.controller.request.CreateProductRequest;
import com.example.shop.controller.request.SearchFilter;
import com.example.shop.model.ProductDto;
import com.example.shop.service.request.ImmutableUpdateProductRequest;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<ProductDto> productList(Integer offset,Integer limit);

    UUID save(CreateProductRequest request);

    ProductDto getProductById(UUID id);

    void deleteProductById(UUID id);

    ProductDto updateProduct(final UUID id, final ImmutableUpdateProductRequest request);

    void updatePriceForProduct(final Double newPrice);

    List<ProductDto> findProductEntityToFilter(SearchFilter filter);

}
