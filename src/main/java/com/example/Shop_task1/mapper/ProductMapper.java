package com.example.Shop_task1.mapper;

import com.example.Shop_task1.data.dto.CreateProductRequest;
import com.example.Shop_task1.data.dto.GetProductResponse;
import com.example.Shop_task1.data.ProductEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductEntity createProductRequest(CreateProductRequest createProductRequest);

    GetProductResponse getProduct(ProductEntity product);

    List<GetProductResponse> listProduct(List<ProductEntity> productEntityList);

    ProductEntity updateProduct(GetProductResponse response);

}
