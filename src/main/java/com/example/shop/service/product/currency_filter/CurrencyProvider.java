package com.example.shop.service.product.currency_filter;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@Data
@SessionScope
public class CurrencyProvider {

    private Currency currency;

    public CurrencyProvider() {
        this.currency = Currency.RUB;
    }
}
