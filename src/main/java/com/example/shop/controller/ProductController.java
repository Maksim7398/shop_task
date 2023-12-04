package com.example.shop.controller;

import com.example.shop.controller.request.CreateProductRequest;
import com.example.shop.controller.request.UpdateProductRequest;
import com.example.shop.controller.response.GetProductResponse;
import com.example.shop.exeption.ProductNotFoundExeption;
import com.example.shop.mapper.ProductMapper;
import com.example.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    private final ProductMapper mapper;

    @GetMapping("/products")
    public List<GetProductResponse> listProducts() {
        return mapper.listProductToResponse(service.productList());
    }
    @PostMapping("/product")
    public UUID createProduct (@RequestBody CreateProductRequest createProductRequest) {

            return service.save(createProductRequest);
    }
    @SneakyThrows
    @GetMapping("/product/{id}")
    public GetProductResponse getProduct(@PathVariable UUID id) {
        return ResponseEntity.ok(mapper.convertFromDto(service.getProductById(id))).getBody();
    }
    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteByID(@PathVariable UUID id) {
        try {
            service.deleteProductById(id);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ProductNotFoundExeption ex) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    @PutMapping("/product/{id}")
    public GetProductResponse updateProduct(@RequestBody UpdateProductRequest product, @PathVariable UUID id) {
        return mapper.convertFromDto(service.updateProduct(id, product));
    }
}
