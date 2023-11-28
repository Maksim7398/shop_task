package com.example.shop.controller;

import com.example.shop.exeption.ProductNotFoundExeption;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandlerController {
    @ExceptionHandler(ProductNotFoundExeption.class)
    public String handleEmptySearchException(ProductNotFoundExeption exception) {
        return exception.getMessage();
    }
}
