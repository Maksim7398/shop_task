package com.example.shop.currency.request_filter;

import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateValue {

    private BigDecimal RUB;
    private BigDecimal USD;
    private BigDecimal EUR;

}
