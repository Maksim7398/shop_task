package com.example.Shop_task1.exeption;

public class ProductNotFoundExeption extends RuntimeException {

    public ProductNotFoundExeption(String id) {
        super("Product with id = " + id + "  not found");
    }
}
