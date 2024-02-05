package com.example.shop.currency.request_filter;

import com.example.shop.controller.response.GetProductResponse;
import lombok.RequiredArgsConstructor;
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
public class CurrencyExchangeAdvice implements ResponseBodyAdvice<GetProductResponse> {

    private final ExchangeRateProvider exchangeRateProvider;

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
                .divide(exchangeRateProvider.getExchangeValue(), RoundingMode.HALF_UP)));

        return body;
    }
}
