package com.example.shop.mapper;

import com.example.shop.controller.product.request.CreateProductRequest;
import com.example.shop.controller.product.request.UpdateProductRequest;
import com.example.shop.controller.product.response.GetProductResponse;
import com.example.shop.controller.product.response.UpdateProductResponse;
import com.example.shop.model.Category;
import com.example.shop.model.ProductDto;
import com.example.shop.persist.entity.ProductEntity;
import com.example.shop.service.product.request.ImmutableUpdateProductRequest;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-04T16:42:02+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductEntity createProductRequest(CreateProductRequest createProductRequest) {
        if ( createProductRequest == null ) {
            return null;
        }

        ProductEntity.ProductEntityBuilder productEntity = ProductEntity.builder();

        productEntity.article( createProductRequest.getArticle() );
        productEntity.title( createProductRequest.getTitle() );
        productEntity.description( createProductRequest.getDescription() );
        productEntity.category( createProductRequest.getCategory() );
        productEntity.price( createProductRequest.getPrice() );
        productEntity.quantity( createProductRequest.getQuantity() );
        productEntity.isAvailable( createProductRequest.getIsAvailable() );

        return productEntity.build();
    }

    @Override
    public ProductDto convertFromEntityToDto(ProductEntity product) {
        if ( product == null ) {
            return null;
        }

        ProductDto.ProductDtoBuilder productDto = ProductDto.builder();

        productDto.id( product.getId() );
        productDto.article( product.getArticle() );
        productDto.title( product.getTitle() );
        productDto.description( product.getDescription() );
        productDto.category( product.getCategory() );
        productDto.price( product.getPrice() );
        productDto.quantity( product.getQuantity() );
        productDto.lastQuantityChange( product.getLastQuantityChange() );
        productDto.createDate( product.getCreateDate() );
        productDto.isAvailable( product.getIsAvailable() );

        return productDto.build();
    }

    @Override
    public List<ProductDto> convertListEntityToListDto(List<ProductEntity> productEntityList) {
        if ( productEntityList == null ) {
            return null;
        }

        List<ProductDto> list = new ArrayList<ProductDto>( productEntityList.size() );
        for ( ProductEntity productEntity : productEntityList ) {
            list.add( convertFromEntityToDto( productEntity ) );
        }

        return list;
    }

    @Override
    public List<GetProductResponse> listProductToResponse(List<ProductDto> productDto) {
        if ( productDto == null ) {
            return null;
        }

        List<GetProductResponse> list = new ArrayList<GetProductResponse>( productDto.size() );
        for ( ProductDto productDto1 : productDto ) {
            list.add( convertFromDto( productDto1 ) );
        }

        return list;
    }

    @Override
    public GetProductResponse convertFromDto(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        GetProductResponse getProductResponse = new GetProductResponse();

        getProductResponse.setId( productDto.getId() );
        getProductResponse.setArticle( productDto.getArticle() );
        getProductResponse.setTitle( productDto.getTitle() );
        getProductResponse.setDescription( productDto.getDescription() );
        getProductResponse.setCategory( productDto.getCategory() );
        getProductResponse.setPrice( productDto.getPrice() );
        getProductResponse.setQuantity( productDto.getQuantity() );
        getProductResponse.setLastQuantityChange( productDto.getLastQuantityChange() );
        getProductResponse.setCreateDate( productDto.getCreateDate() );
        getProductResponse.setIsAvailable( productDto.getIsAvailable() );

        return getProductResponse;
    }

    @Override
    public UpdateProductResponse convertFromDtoToResponse(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        UUID id = null;
        String article = null;
        String title = null;
        String description = null;
        Category category = null;
        BigDecimal price = null;
        LocalDateTime lastQuantityChange = null;
        LocalDateTime createDate = null;

        id = productDto.getId();
        article = productDto.getArticle();
        title = productDto.getTitle();
        description = productDto.getDescription();
        category = productDto.getCategory();
        price = productDto.getPrice();
        lastQuantityChange = productDto.getLastQuantityChange();
        createDate = productDto.getCreateDate();

        UpdateProductResponse updateProductResponse = new UpdateProductResponse( id, article, title, description, category, price, lastQuantityChange, createDate );

        updateProductResponse.setQuantity( productDto.getQuantity() );
        updateProductResponse.setIsAvailable( productDto.getIsAvailable() );

        return updateProductResponse;
    }

    @Override
    public ImmutableUpdateProductRequest convertFromUpdateToImmutableRequest(UpdateProductRequest request) {
        if ( request == null ) {
            return null;
        }

        ImmutableUpdateProductRequest.ImmutableUpdateProductRequestBuilder immutableUpdateProductRequest = ImmutableUpdateProductRequest.builder();

        immutableUpdateProductRequest.article( request.getArticle() );
        immutableUpdateProductRequest.title( request.getTitle() );
        immutableUpdateProductRequest.description( request.getDescription() );
        immutableUpdateProductRequest.category( request.getCategory() );
        immutableUpdateProductRequest.price( request.getPrice() );
        immutableUpdateProductRequest.quantity( request.getQuantity() );
        immutableUpdateProductRequest.isAvailable( request.getIsAvailable() );

        return immutableUpdateProductRequest.build();
    }
}
