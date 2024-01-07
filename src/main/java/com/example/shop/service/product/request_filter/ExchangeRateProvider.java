package com.example.shop.service.product.request_filter;

import com.example.shop.service.exchange_service.ExchangeRateValue;
import com.example.shop.service.exchange_service.ExchangeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ExchangeRateProvider {

    private final ExchangeService exchangeService;

    public Double getExchange(){
       return Optional.ofNullable(exchangeService.getExchangeRate()).orElseGet(this ::getExchangeRate);
    }

    public Double getExchangeRate() {
        try {
            File file = new File("src/main/resources/exchange_rate.json");
            final ObjectMapper objectMapper = new ObjectMapper();
            final ExchangeRateValue exchangeRate = objectMapper.readValue(file, ExchangeRateValue.class);
            return exchangeRate.getExchangeRate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
