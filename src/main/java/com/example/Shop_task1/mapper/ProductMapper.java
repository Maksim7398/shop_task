package com.example.Shop_task1.mapper;

import com.example.Shop_task1.data.Product;
import com.example.Shop_task1.data.ProductDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product createProductRequest(ProductDto productDto);

    Product updateProductRequest(ProductDto productDto);



}
