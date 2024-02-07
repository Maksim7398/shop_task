package com.example.shop.currency.request_filter;

import com.example.shop.controller.response.GetProductResponse;
import com.example.shop.service.product.currency_filter.CurrencyProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.math.RoundingMode;
import java.util.Optional;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class CurrencyExchangeAdvice implements ResponseBodyAdvice<GetProductResponse> {

    private final ExchangeRateProvider exchangeRateProvider;

    private final CurrencyProvider currencyProvider;

    @Override
    public boolean supports(MethodParameter returnType,
                            @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
       return Optional.ofNullable(returnType.getMethod())
                .map(method -> method.toString().contains("ProductController.getProductById"))
               .orElse(false);
    }

    @Override
    public GetProductResponse beforeBodyWrite(@Nullable GetProductResponse body,
                                              @NonNull MethodParameter returnType,
                                              @NonNull MediaType selectedContentType,
                                              @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                              @NonNull ServerHttpRequest request,
                                              @NonNull ServerHttpResponse response) {

        Optional.ofNullable(body)
                .ifPresent(b -> b.setPrice(b.getPrice()
                .divide(exchangeRateProvider.getExchangeValue(currencyProvider.getCurrency()), RoundingMode.HALF_UP)));
        log.info("value from advice " +  exchangeRateProvider.getExchangeValue(currencyProvider.getCurrency()).toString());

        return body;
    }
}
