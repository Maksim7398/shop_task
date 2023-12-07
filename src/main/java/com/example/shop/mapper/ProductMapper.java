package com.example.shop.mapper;

import com.example.shop.controller.request.CreateProductRequest;
import com.example.shop.controller.request.UpdateProductRequest;
import com.example.shop.controller.response.GetProductResponse;
import com.example.shop.controller.response.UpdateProductResponse;
import com.example.shop.model.ProductDto;
import com.example.shop.persist.entity.ProductEntity;
import com.example.shop.service.request.ImmutableUpdateProductRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductEntity createProductRequest(CreateProductRequest createProductRequest);

    ProductDto getProduct(ProductEntity product);

    List<ProductDto> listProduct(List<ProductEntity> productEntityList);

    List<GetProductResponse> listProductToResponse(List<ProductDto> productDto);

    GetProductResponse convertFromDto(ProductDto productDto);

    UpdateProductResponse convertFromDtoToResponse(ProductDto productDto);

    ImmutableUpdateProductRequest convertFromUpdateToImmutableRequest(UpdateProductRequest request);

    ImmutableUpdateProductRequest convertForUpdateRequest(ProductDto productDto);

}