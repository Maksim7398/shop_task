package com.example.Shop_task1.controller;


import com.example.Shop_task1.data.Categories;
import com.example.Shop_task1.data.Product;
import com.example.Shop_task1.data.ProductDto;
//import com.example.Shop_task1.mapper.ProductMapper;
import com.example.Shop_task1.mapper.ProductMapper;
import com.example.Shop_task1.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;


@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    private final ProductMapper productMapper;


    @GetMapping("/create_product")
    public void getProduct(@RequestBody ProductDto productDto){
        Product product = productMapper.createProd(productDto);
        service.save(product);
    }


}
