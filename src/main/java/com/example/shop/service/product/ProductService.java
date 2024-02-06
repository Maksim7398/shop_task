package com.example.shop.service.product;

import com.example.shop.controller.request.CreateProductRequest;
import com.example.shop.controller.request.SearchFilter;
import com.example.shop.model.ProductDto;
import com.example.shop.service.product.request.ImmutableUpdateProductRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<ProductDto> productList(Pageable pageable);

    UUID save(CreateProductRequest request);

    ProductDto getProductById(UUID id);

    void deleteProductById(UUID id);

    ProductDto updateProduct(final UUID id, final ImmutableUpdateProductRequest request);

    void updatePriceForProduct(final Double newPrice) throws InterruptedException;

    List<ProductDto> findProductEntityToFilter(SearchFilter filter);

}
