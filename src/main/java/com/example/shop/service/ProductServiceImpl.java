package com.example.shop.service;

import com.example.shop.controller.request.CreateProductRequest;
import com.example.shop.exception.ArticleAlreadyExistsException;
import com.example.shop.exception.ProductNotFoundException;
import com.example.shop.mapper.ProductMapper;
import com.example.shop.model.ProductDto;
import com.example.shop.persist.entity.ProductEntity;
import com.example.shop.persist.repository.ProductRepository;
import com.example.shop.service.request.ImmutableUpdateProductRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;

    @Override
    public List<ProductDto> productList() {
        return mapper.listProduct(repository.findAll());
    }

    @Override
    public UUID save(final CreateProductRequest request) {
        if (repository.existsByArticle(request.getArticle())) {
            throw new ArticleAlreadyExistsException("продукт с таким артикулом уже существует");
        }
        final ProductEntity productEntity = mapper.createProductRequest(request);
        productEntity.setLastQuantityChange(LocalDateTime.now());
        repository.save(productEntity);
        log.info(productEntity + "saved");

        return productEntity.getId();
    }

    @Override
    public ProductDto getProductById(final UUID id) {
        return mapper.getProduct(repository.findById(id).orElseThrow(
                () -> new ProductNotFoundException("there is no product with this ID")
        ));
    }

    @Override
    public void deleteProductById(final UUID id) {
        repository.findById(id).orElseThrow(
                () -> new ProductNotFoundException("there is no product with this ID")
        );
        repository.deleteById(id);
    }

    @Override
    public ProductDto updateProduct(final UUID id, final ImmutableUpdateProductRequest request) {
        final ProductEntity productEntity = repository.findById(id).orElseThrow(
                () -> new ProductNotFoundException("there is no product with this ID")
        );
        log.info(productEntity.toString());
        if (!productEntity.getQuantity().equals(request.getQuantity())) {
            productEntity.setLastQuantityChange(LocalDateTime.now());
            log.info("quantity was be  changed");
        } else if (repository.existsByArticle(request.getArticle()) && !request.getArticle().equals(productEntity.getArticle())) {
            throw new ArticleAlreadyExistsException("продукт с таким артикулом уже существует");
        }
        productEntity.setArticle(request.getArticle());
        productEntity.setTitle(request.getTitle());
        productEntity.setQuantity(request.getQuantity());
        productEntity.setCategory(request.getCategory());
        productEntity.setDescription(request.getDescription());
        productEntity.setPrice(request.getPrice());
        repository.save(productEntity);
        log.debug(productEntity.toString());

        return mapper.getProduct(productEntity);
    }
}


