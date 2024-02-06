package com.example.shop.service.interaction;

import com.example.shop.currency.request_filter.ExchangeRateValue;

public interface ExchangeServiceClient {
    ExchangeRateValue getExchangeRate();
}
