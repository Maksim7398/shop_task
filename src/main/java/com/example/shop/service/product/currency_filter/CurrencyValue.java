package com.example.shop.service.product.currency_filter;

import com.example.shop.currency.request_filter.ExchangeRateValue;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.math.BigDecimal;


@Component
@Data
@Slf4j
@SessionScope
public class CurrencyValue {

    private Currency currency;

    public @Nullable BigDecimal getRate(ExchangeRateValue exchangeRateValue) {
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
