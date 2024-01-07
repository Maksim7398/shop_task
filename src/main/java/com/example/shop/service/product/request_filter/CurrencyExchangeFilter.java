//package com.example.shop.service.product.request_filter;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import lombok.SneakyThrows;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.util.stream.Collectors;
//
//
//@Component
//@RequiredArgsConstructor
//@Slf4j
//public class CurrencyExchangeFilter extends OncePerRequestFilter {
//
//    private final ReadExchangeRate exchangeRate;
//
//    @Override
//    @SneakyThrows
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
//        String servletPath = request.getServletPath();
//        if (servletPath.startsWith("/product/") && "GET".equalsIgnoreCase(request.getMethod())){
//            String collect = request.getReader().lines().filter(val -> val.contains("body")).collect(Collectors.joining(System.lineSeparator()));
//        }
//        filterChain.doFilter(request,response);
//    }
//
//
//
//}
