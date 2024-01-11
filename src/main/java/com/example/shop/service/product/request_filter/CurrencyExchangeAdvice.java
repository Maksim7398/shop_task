package com.example.shop.service.product.request_filter;

import com.example.shop.controller.response.GetProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class CurrencyExchangeAdvice implements ResponseBodyAdvice<GetProductResponse> {

    private final ExchangeRateProvider exchangeRate;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return Objects.requireNonNull(returnType.getMethod()).toString().contains("ProductController.getProductById");
    }

    @Override
    @SneakyThrows
    public GetProductResponse beforeBodyWrite(@Nullable GetProductResponse body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        log.info(exchangeRate.getExchangeValue().toString());
        final BigDecimal price = body.getPrice().divide(new BigDecimal(exchangeRate.getExchangeValue()), 2, RoundingMode.HALF_UP);
        body.setPrice(price);

        return body;
    }
}
