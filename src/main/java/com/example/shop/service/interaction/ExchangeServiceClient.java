package com.example.shop.service.interaction;

import com.example.shop.currency.request_filter.ExchangeRateValue;

import java.util.List;
import java.util.Map;

public interface ExchangeServiceClient {

    ExchangeRateValue getExchangeRate();

    Map<String,String> getAllInnByEmail(List<String> email);
}
