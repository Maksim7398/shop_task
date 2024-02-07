package com.example.shop.service.product.currency_filter;

public enum Currency {
    RUB,
    USD,
    EUR;

    public static Currency checkName(String name) {
        for (Currency currency : Currency.values()) {
            if (name != null) {
                if (name.equals(currency.name())) {
                    return currency;
                }
            }
        }
        return null;
    }
}
