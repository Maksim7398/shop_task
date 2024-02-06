package com.example.shop.service.interaction;

import com.example.shop.currency.request_filter.ExchangeRateValue;
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
public class ExchangeServiceClientImpl implements ExchangeServiceClient {

    private final RestTemplate restTemplate;

    @Override
    @Nullable
    @Cacheable(unless = "#result == null", value = "exchangeValue")
    public ExchangeRateValue getExchangeRate() {
        try {
            final String uri = "/exchangeValue";

            return restTemplate.getForObject(uri, ExchangeRateValue.class);
        } catch (Exception ex) {
            log.error("Ошибка при подключению к сервису: " + ex.getMessage());

            return null;
        }
    }

}
