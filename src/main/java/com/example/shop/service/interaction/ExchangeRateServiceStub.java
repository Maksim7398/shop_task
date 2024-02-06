package com.example.shop.service.interaction;

import com.example.shop.currency.request_filter.ExchangeRateValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@ConditionalOnProperty(value = "interaction.rate.stub")
@Slf4j
@Primary
public class ExchangeRateServiceStub implements ExchangeServiceClient {

    @Override
    public ExchangeRateValue getExchangeRate() {
        final ExchangeRateValue exchangeRateValue = new ExchangeRateValue(new BigDecimal("50.0"));
        log.info("exchange rate value from service stub: " + exchangeRateValue.getExchangeRate().toString());

        return exchangeRateValue;
    }
}
