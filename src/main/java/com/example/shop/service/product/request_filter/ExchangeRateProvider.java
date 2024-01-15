package com.example.shop.service.product.request_filter;

import com.example.shop.service.interaction.ExchangeServiceClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class ExchangeRateProvider {

    private final ExchangeServiceClient exchangeService;

    private final CurrencyValue currencyValue;

    public Double getExchangeValue() {
        return Optional.ofNullable(getExchangeRateFromService())
                .orElseGet(this::getExchangeRateFromFile);
    }

    private @Nullable Double getExchangeRateFromFile() {
        try {
            final Resource resource = new ClassPathResource("exchangeRate.json");
            final ObjectMapper objectMapper = new ObjectMapper();
            final ExchangeRateValue exchangeRateValue =
                    objectMapper.readValue(resource.getInputStream(),
                            ExchangeRateValue.class);
            return currencyValue.getRate(exchangeRateValue);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private @Nullable Double getExchangeRateFromService() {
        return currencyValue.getRate(exchangeService.getExchangeRate());
    }
}
