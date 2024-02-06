package com.example.shop.currency.request_filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateValue {

    private BigDecimal RUB;
    private BigDecimal USD;
    private BigDecimal EUR;

}
