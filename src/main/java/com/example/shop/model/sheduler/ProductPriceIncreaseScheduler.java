package com.example.shop.model.sheduler;

import com.example.shop.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(value = "app.scheduling.enabled", havingValue = "true")
@Slf4j
public class ProductPriceIncreaseScheduler {

    private final ProductService service;
    @Value("${app.scheduling.percent}")
    private Double percent;

    @Scheduled(fixedDelayString = "${app.scheduling.period}")
    public void priceIncrease() throws InterruptedException {
        service.updatePriceForProduct(percent);
    }
}
