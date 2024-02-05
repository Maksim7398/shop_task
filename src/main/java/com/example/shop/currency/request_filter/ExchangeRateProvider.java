package com.example.shop.currency.request_filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
@Slf4j
public class ExchangeRateProvider {

    public Double getExchangeRate() {
        try {
            final File file = new File("src/main/resources/exchangeRate.json");
            final ObjectMapper objectMapper = new ObjectMapper();
            final ExchangeRateValue exchangeRateValue = objectMapper.readValue(file, ExchangeRateValue.class);
            return exchangeRateValue.getExchangeRate();
        } catch (IOException e) {
            log.debug(e.getMessage());
            return 1.0;
        }
    }
}
