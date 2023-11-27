package com.example.shop.service;

import com.example.shop.model.ProductDto;
import com.example.shop.persist.entity.ProductEntity;
import com.example.shop.persist.repository.ProductRepository;
import com.example.shop.controller.request.CreateProductRequest;
import com.example.shop.exeption.ProductNotFoundExeption;
import com.example.shop.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    private final ProductMapper mapper;

    public final List<ProductDto> poductList() {
        return mapper.listProduct(repository.findAll());
    }

    public final UUID save(final CreateProductRequest product) {
        final ProductEntity productEntity = mapper.createProductRequest(product);
        productEntity.setLastQuantityChange(LocalDateTime.now());
        repository.save(productEntity);
        log.info(productEntity + "saved");
        return productEntity.getId();
    }

    public final ProductDto getProductById(final UUID id) {
        return mapper.getProduct(repository.findById(id).orElseThrow(
                () -> new ProductNotFoundExeption("there is no product with this ID")
        ));
    }

    public final void deleteProductById(final UUID id) {
        repository.findById(id).orElseThrow(
                () -> new ProductNotFoundExeption("there is no product with this ID")
        );
        repository.deleteById(id);
    }

    public final UUID updateProduct(final UUID id, final CreateProductRequest p) {
        final ProductEntity productEntity = mapper.updateProduct(getProductById(id));
        log.info(productEntity.toString());
        if (!productEntity.getQuantity().equals(p.getQuantity())) {
            productEntity.setLastQuantityChange(LocalDateTime.now());
            log.info("quantity was be  changed");
        }

        productEntity.setTitle(p.getTitle());
        productEntity.setQuantity(p.getQuantity());
        productEntity.setCategories(p.getCategories());
        productEntity.setDescription(p.getDescription());
        productEntity.setPrice(p.getPrice());
        repository.save(productEntity);
        log.info(productEntity.toString());

        return productEntity.getId();
    }
}


