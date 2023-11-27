package com.example.Shop_task1.controller;



import com.example.Shop_task1.data.dto.CreateProductRequest;
import com.example.Shop_task1.data.dto.GetProductResponse;

import com.example.Shop_task1.exeption.ProductNotFoundExeption;
import com.example.Shop_task1.service.ProductService;
import lombok.RequiredArgsConstructor;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping("/all_products")
    public List<GetProductResponse> listProducts(){
        return service.poductList();
    }


    @PostMapping("/create_product")
    public UUID createProduct(@RequestBody CreateProductRequest createProductRequest){
        return service.save(createProductRequest);
    }
    @GetMapping("/{id}")
    public GetProductResponse getProduct(@PathVariable String id){
        return service.getProductById(id);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Void> deleteByID(@PathVariable String id){
         try {
             service.deleteProdById(id);
             return new ResponseEntity<>(HttpStatus.OK);
         }catch (ProductNotFoundExeption ex){
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }
    }
    @PutMapping("/update/{id}")
    public UUID updateProduct(@RequestBody CreateProductRequest product, @PathVariable String id){
        return service.updateProduct(id,product);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundExeption.class)
    public String companyNotFound(ProductNotFoundExeption e) {
        return e.getMessage();
    }




}
