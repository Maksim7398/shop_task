package com.example.shop.currency.request_filter;

import com.example.shop.service.interaction.ExchangeServiceClientImpl;
import com.example.shop.service.product.currency_filter.Currency;
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

    private final BigDecimal DEFAULT_EXCHANGE_RATE = new BigDecimal("1.0");

    public BigDecimal getExchangeValue(Currency currency) {
        return Optional.ofNullable(getRate(getExchangeRateFromService(),currency))
                .orElseGet( () ->
                Optional.ofNullable(getRate(getExchangeRateFromFile(),currency))
                .orElse(DEFAULT_EXCHANGE_RATE));
    }

    public @Nullable ExchangeRateValue getExchangeRateFromFile() {
        try {
            final Resource resource = new ClassPathResource("exchangeRate.json");
            final ObjectMapper objectMapper = new ObjectMapper();
            final ExchangeRateValue exchangeRateValue =
                    objectMapper.readValue(resource.getInputStream(),
                            ExchangeRateValue.class);
            log.info("value from file: " + exchangeRateValue);

            return exchangeRateValue;
        } catch (IOException e) {
            log.error("Ошибка при чтении из файла: " + e.getMessage());

            return null;
        }
    }

    private @Nullable ExchangeRateValue getExchangeRateFromService() {
        final ExchangeRateValue result = exchangeService.getExchangeRate();
        log.info("value from service: " + result);

        return result;
    }

    private @Nullable static BigDecimal getRate(ExchangeRateValue exchangeRateValue, Currency currency) {
        if (exchangeRateValue == null) {
            return null;
        }
        log.info(currency.name());

        return switch (currency) {
            case EUR -> exchangeRateValue.getEUR();
            case RUB -> exchangeRateValue.getRUB();
            case USD -> exchangeRateValue.getUSD();
        };
    }

}
