package com.example.shop.service.interaction;

import com.example.shop.service.product.request_filter.ExchangeRateValue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor
@Slf4j
@CacheConfig
public class ExchangeServiceClient {

    private final RestTemplate restTemplate;

    @Nullable
    @Cacheable(unless = "#result == null", value = "exchangeValue")
    public ExchangeRateValue getExchangeRate() {
        try {
            String uri = "/exchangeValue";
            ExchangeRateValue exchangeRateValue = restTemplate.getForObject(uri, ExchangeRateValue.class);
            log.info("exchange rate value from service: " + exchangeRateValue.getExchangeRate().toString());
            return exchangeRateValue;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }

}
