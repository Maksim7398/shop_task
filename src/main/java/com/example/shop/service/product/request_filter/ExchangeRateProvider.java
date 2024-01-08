package com.example.shop.service.product.request_filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class ExchangeRateProvider {

    public Double getExchangeRate() {
        try {
            final File file = new File("src/main/resources/exchangeRate.json");
            final ObjectMapper objectMapper = new ObjectMapper();
            final ExchangeRateValue exchangeRateValue = objectMapper.readValue(file, ExchangeRateValue.class);
            return exchangeRateValue.getExchangeRate();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
