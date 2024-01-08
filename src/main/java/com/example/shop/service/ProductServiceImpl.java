package com.example.shop.service;

import com.example.shop.controller.request.CreateProductRequest;
import com.example.shop.controller.request.SearchFilter;
import com.example.shop.exception.ArticleAlreadyExistsException;
import com.example.shop.exception.ProductNotFoundException;
import com.example.shop.mapper.ProductMapper;
import com.example.shop.model.ProductDto;
import com.example.shop.persist.entity.ProductEntity;
import com.example.shop.persist.repository.ProductRepository;
import com.example.shop.persist.specification.ProductSpecification;
import com.example.shop.service.annotation.CheckTime;
import com.example.shop.service.request.ImmutableUpdateProductRequest;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;
    private final ProductSpecification specification;

    @CheckTime
    @Override
    public List<ProductDto> productList(Pageable pageable) {
        Page<ProductEntity> allProduct = repository.findAll(pageable);
        if (allProduct.getContent().isEmpty()) {
            throw new ProductNotFoundException("list products is empty");
        } else {
            allProduct.getContent();
        }
        return mapper.convertListEntityToListDto(allProduct.getContent());
    }

    @Override
    @CheckTime
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
    @CheckTime
    public ProductDto getProductById(final UUID id) {
        return mapper.convertFromEntityToDto(repository.findById(id).orElseThrow(
                () -> new ProductNotFoundException("there is no product with this ID")
        ));
    }

    @Override
    @CheckTime
    public void deleteProductById(final UUID id) {
        repository.findById(id).orElseThrow(
                () -> new ProductNotFoundException("there is no product with this ID")
        );
        repository.deleteById(id);
    }

    @Override
    @CheckTime
    public ProductDto updateProduct(final UUID id, final ImmutableUpdateProductRequest request) {
        final ProductEntity productEntity = repository.findById(id).orElseThrow(
                () -> new ProductNotFoundException("there is no product with this ID")
        );
        log.info(productEntity.toString());
        if (!productEntity.getQuantity().equals(request.getQuantity())) {
            productEntity.setLastQuantityChange(LocalDateTime.now());
            log.info("quantity was be  changed");
        }
        if (repository.existsByArticle(request.getArticle()) && !request.getArticle().equals(productEntity.getArticle())) {
            throw new ArticleAlreadyExistsException("продукт с таким артикулом уже существует");
        }
        productEntity.setIsAvailable(request.getIsAvailable());
        productEntity.setArticle(request.getArticle());
        productEntity.setTitle(request.getTitle());
        productEntity.setQuantity(request.getQuantity());
        productEntity.setCategory(request.getCategory());
        productEntity.setDescription(request.getDescription());
        productEntity.setPrice(request.getPrice());
        repository.save(productEntity);
        log.debug(productEntity.toString());

        return mapper.convertFromEntityToDto(productEntity);
    }

    @Override
    @CheckTime
    @Transactional
    public void updatePriceForProduct(final Double percent) throws InterruptedException {
        log.info("Scheduled job start");
        List<ProductEntity> list = repository.findAll();
        for (ProductEntity productEntity : list) {
            BigDecimal price = productEntity.getPrice().multiply(new BigDecimal(String.valueOf(percent))).add(productEntity.getPrice());
            productEntity.setPrice(price);
            repository.save(productEntity);
            log.debug(price + " sum exchange");
        }
        Thread.sleep(10000);
        log.info("Scheduled job finish");
    }

    @CheckTime
    @Override
    public List<ProductDto> findProductEntityToFilter(SearchFilter filter) {
        return mapper.convertListEntityToListDto(repository.findAll(specification.getProduct(filter)));
    }

}


