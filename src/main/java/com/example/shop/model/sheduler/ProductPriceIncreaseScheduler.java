package com.example.shop.model.sheduler;

import com.example.shop.controller.request.UpdateProductRequest;
import com.example.shop.mapper.ProductMapper;
import com.example.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
@RequiredArgsConstructor
@ConditionalOnProperty(value = "app.scheduling.enabled",havingValue = "true")
@Slf4j
public class ProductPriceIncreaseScheduler {

    private final ProductService service;

    private final ProductMapper mapper;

    @Scheduled(fixedDelayString = "${app.scheduling.period}")
    public void priceIncrease(){
        service.productList().forEach(productEntity -> {
           BigDecimal add = productEntity.getPrice().multiply(new BigDecimal("0.1")).add(productEntity.getPrice());
            UpdateProductRequest updateProductRequest = mapper.updateProdReq(productEntity);
            updateProductRequest.setPrice(add);
            service.updateProduct(productEntity.getId(),updateProductRequest);
        });
    }
}
