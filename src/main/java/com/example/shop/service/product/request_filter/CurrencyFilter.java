package com.example.shop.service.product.request_filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
@RequiredArgsConstructor
@Slf4j
public class CurrencyFilter extends OncePerRequestFilter {

    private final CurrencyValue currencyValue;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String currency = request.getHeader("currency");
        if (currency != null && !currency.isEmpty()) {
            currencyValue.setCurrency(Currency.valueOf(currency));
        } else {
            currencyValue.setCurrency(Currency.RUB);
        }
        filterChain.doFilter(request, response);
    }

}



