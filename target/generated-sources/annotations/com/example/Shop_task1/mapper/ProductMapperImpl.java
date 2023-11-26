package com.example.Shop_task1.mapper;

import com.example.Shop_task1.data.Product;
import com.example.Shop_task1.data.ProductDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-27T00:27:44+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product createProductRequest(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        Product product = new Product();

        product.setTitle( productDto.getTitle() );
        product.setDescription( productDto.getDescription() );
        product.setCategories( productDto.getCategories() );
        product.setPrice( productDto.getPrice() );
        product.setCount( productDto.getCount() );

        return product;
    }

    @Override
    public Product updateProductRequest(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        Product product = new Product();

        product.setTitle( productDto.getTitle() );
        product.setDescription( productDto.getDescription() );
        product.setCategories( productDto.getCategories() );
        product.setPrice( productDto.getPrice() );
        product.setCount( productDto.getCount() );

        return product;
    }
}
