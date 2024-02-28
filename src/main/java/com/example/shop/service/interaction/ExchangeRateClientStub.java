package com.example.shop.service.interaction;

import com.example.shop.currency.request_filter.ExchangeRateValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
@ConditionalOnProperty(value = "interaction.rate.stub")
@Slf4j
@Primary
public class ExchangeRateClientStub implements ExchangeServiceClient {

    @Override
    public ExchangeRateValue getExchangeRate() {
        final ExchangeRateValue exchangeRateValue = new ExchangeRateValue(
                new BigDecimal("1.00"),
                new BigDecimal("50.00"),
                new BigDecimal("100.00"));
        log.info("exchange rate value from service stub: " + exchangeRateValue);

        return exchangeRateValue;
    }

    @Override
    public Map<String,String> getAllInnByEmail(List<String> email) {
        return Map.of();
    }
}