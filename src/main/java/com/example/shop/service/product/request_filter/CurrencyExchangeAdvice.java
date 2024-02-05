package com.example.shop.service.product.request_filter;

import com.example.shop.controller.response.GetProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@RestControllerAdvice
@RequiredArgsConstructor
public class CurrencyExchangeAdvice implements ResponseBodyAdvice<GetProductResponse> {

    private final ExchangeRateProvider exchangeRate;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return Objects.requireNonNull(returnType.getMethod()).toString().contains("ProductController.getProductById");
    }

    @Override
    public GetProductResponse beforeBodyWrite(@Nullable GetProductResponse body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        final BigDecimal price = Objects.requireNonNull(body).getPrice().divide(BigDecimal.valueOf(exchangeRate.getExchangeRate()), 2, RoundingMode.HALF_UP);
        body.setPrice(price);

        return body;
    }
}
