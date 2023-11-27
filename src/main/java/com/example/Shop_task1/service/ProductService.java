package com.example.Shop_task1.service;

import com.example.Shop_task1.data.*;
import com.example.Shop_task1.data.dto.CreateProductRequest;
import com.example.Shop_task1.data.dto.GetProductResponse;
import com.example.Shop_task1.exeption.ProductNotFoundExeption;
import com.example.Shop_task1.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;


@Service
@RequiredArgsConstructor
public class ProductService {


    private final ProductRepository repository;

    private final ProductMapper mapper;


    Logger logger = Logger.getLogger(ProductService.class.getSimpleName());

    public List<GetProductResponse> poductList() {
        return mapper.listProduct(repository.findAll());
    }

    public UUID save(CreateProductRequest product) {
        ProductEntity productEntity = mapper.createProductRequest(product);
        repository.save(productEntity);
        return productEntity.getId();
    }

    public GetProductResponse getProductById(String id) {
        return mapper.getProduct(repository.findById(UUID.fromString(id)).orElseThrow(
                () -> new ProductNotFoundExeption(id)
        ));
    }

    public void deleteProdById(String id) {
        try {
            getProductById(id);
            repository.deleteById(UUID.fromString(id));
        } catch (ProductNotFoundExeption ex) {
            throw new ProductNotFoundExeption(id);
        }

    }


    public UUID updateProduct(String id, CreateProductRequest p) {
        ProductEntity productEntity = mapper.updateProduct(getProductById(id));
        logger.info(productEntity.toString());
        productEntity.setTitle(p.getTitle());
        productEntity.setCount(p.getCount());
        productEntity.setCategories(p.getCategories());
        productEntity.setDescription(p.getDescription());
        productEntity.setPrice(p.getPrice());
        repository.save(productEntity);
        logger.info(productEntity.toString());
        return productEntity.getId();
    }
}


