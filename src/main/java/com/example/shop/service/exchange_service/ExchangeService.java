package com.example.shop.service.exchange_service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "exchangeValue")
public class ExchangeService {

    private final RestTemplate restTemplate;

    @Cacheable(unless = "#result == null")
    public Double getExchangeRate(){
        String uri = "/exchangeValue";
        ExchangeRateValue exchangeRateValue = restTemplate.getForObject(uri,ExchangeRateValue.class);
        return exchangeRateValue.getExchangeRate();
    }



}
