package com.example.Shop_task1.controller;


import com.example.Shop_task1.data.Product;
import com.example.Shop_task1.data.ProductDto;
//import com.example.Shop_task1.mapper.ProductMapper;
import com.example.Shop_task1.mapper.ProductMapper;
import com.example.Shop_task1.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    private final ProductMapper productMapper;


    @PostMapping("/create_product")
    public void createProduct(@Validated @RequestBody ProductDto productDto){
        Product product = productMapper.createProductRequest(productDto);
        service.save(product);
    }
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable String id){
        return service.getProductById(id);
    }

    @GetMapping("/delete/{id}")
    public void deleteByID(@PathVariable String id){
        service.deleteProdById(id);
    }
    @PutMapping("/update/{id}")
    public void updateProduct(@RequestBody ProductDto productDto,@PathVariable String id){
        Product product = productMapper.updateProductRequest(productDto);
        service.updateProduct(id,product);
    }




}
