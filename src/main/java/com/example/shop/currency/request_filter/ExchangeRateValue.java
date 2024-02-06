package com.example.shop.currency.request_filter;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ExchangeRateValue {
   private BigDecimal exchangeRate;
}
