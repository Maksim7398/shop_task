package com.example.shop.currency.request_filter;

import com.example.shop.service.interaction.ExchangeServiceClientImpl;
import com.example.shop.service.product.currency_filter.CurrencyValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class ExchangeRateProvider {

    private final ExchangeServiceClientImpl exchangeService;

    private final CurrencyValue currencyValue;

    private final BigDecimal DEFAULT_EXCHANGE_RATE = new BigDecimal("1.0");

    public BigDecimal getExchangeValue() {
        return Optional.ofNullable(getExchangeRateFromService()).orElseGet(this::getExchangeRateFromFile);
    }

    public BigDecimal getExchangeRateFromFile() {
        try {
            final Resource resource = new ClassPathResource("exchangeRate.json");
            final ObjectMapper objectMapper = new ObjectMapper();
            final ExchangeRateValue exchangeRateValue =
                    objectMapper.readValue(resource.getInputStream(),
                            ExchangeRateValue.class);
            log.info("value from file: " + exchangeRateValue);

            return currencyValue.getRate(exchangeRateValue);
        } catch (IOException e) {
            log.error("Ошибка при чтении из файла: " + e.getMessage());

            return DEFAULT_EXCHANGE_RATE;
        }
    }

    private @Nullable BigDecimal getExchangeRateFromService() {
        final ExchangeRateValue result = exchangeService.getExchangeRate();
        log.info("value from service: " + result);

        return currencyValue.getRate(result);
    }
}
