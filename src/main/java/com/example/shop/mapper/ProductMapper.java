package com.example.shop.mapper;

import com.example.shop.controller.product.request.CreateProductRequest;
import com.example.shop.controller.product.request.UpdateProductRequest;
import com.example.shop.controller.product.response.GetProductResponse;
import com.example.shop.controller.product.response.UpdateProductResponse;
import com.example.shop.model.ProductDto;
import com.example.shop.persist.entity.ProductEntity;
import com.example.shop.service.product.request.ImmutableUpdateProductRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductEntity createProductRequest(CreateProductRequest createProductRequest);

    ProductDto convertFromEntityToDto(ProductEntity product);

    List<ProductDto> convertListEntityToListDto(List<ProductEntity> productEntityList);

    List<GetProductResponse> listProductToResponse(List<ProductDto> productDto);

    GetProductResponse convertFromDto(ProductDto productDto);

    UpdateProductResponse convertFromDtoToResponse(ProductDto productDto);

    ImmutableUpdateProductRequest convertFromUpdateToImmutableRequest(UpdateProductRequest request);

}