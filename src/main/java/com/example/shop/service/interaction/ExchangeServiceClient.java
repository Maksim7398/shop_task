package com.example.shop.service.interaction;

import com.example.shop.currency.request_filter.ExchangeRateValue;

import java.util.List;

public interface ExchangeServiceClient {

    ExchangeRateValue getExchangeRate();

    List<String> getAllInnByEmail(List<String> email);
}
