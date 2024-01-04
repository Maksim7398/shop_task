package com.example.shop.service.product.request_filter;

import com.example.shop.controller.product.response.GetProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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

@RestControllerAdvice
@RequiredArgsConstructor
public class CurrencyExchange implements ResponseBodyAdvice<GetProductResponse> {

    private final ReadExchangeRate exchangeRate;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.getMethod().toString().contains("ProductController.getProduct");
    }

    @Nullable
    @Override
    @SneakyThrows
    public GetProductResponse beforeBodyWrite(@Nullable GetProductResponse body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        final BigDecimal price = body.getPrice().divide(new BigDecimal(exchangeRate.getExchangeRate()),2, RoundingMode.HALF_UP);
        body.setPrice(price);

        return body;
    }
}
