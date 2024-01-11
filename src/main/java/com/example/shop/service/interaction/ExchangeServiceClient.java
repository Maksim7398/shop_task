package com.example.shop.service.interaction;

import com.example.shop.service.product.request_filter.ExchangeRateValue;

public interface ExchangeServiceClient {
    ExchangeRateValue getExchangeRate();
}
