package com.example.shop.controller;

import com.example.shop.controller.request.CreateProductRequest;
import com.example.shop.controller.request.SearchFilter;
import com.example.shop.controller.request.UpdateProductRequest;
import com.example.shop.controller.response.GetProductResponse;
import com.example.shop.controller.response.UpdateProductResponse;
import com.example.shop.exception.ProductNotFoundException;
import com.example.shop.mapper.ProductMapper;
import com.example.shop.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
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
    public List<GetProductResponse> listProducts(Pageable pageable) {
        return mapper.listProductToResponse(service.productList(pageable));
    }

    @PostMapping("/product")
    public UUID createProduct(@RequestBody @Valid CreateProductRequest createProductRequest) {
        return service.save(createProductRequest);
    }

    @GetMapping("/product/{id}")
    public GetProductResponse getProduct(@PathVariable UUID id) {
        return mapper.convertFromDto(service.getProductById(id));
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteByID(@PathVariable UUID id) {
        try {
            service.deleteProductById(id);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ProductNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/product/{id}")
    public UpdateProductResponse updateProduct(@RequestBody UpdateProductRequest product, @PathVariable UUID id) {
        return mapper.convertFromDtoToResponse(service.updateProduct(id, mapper.convertFromUpdateToImmutableRequest(product)));
    }

    @GetMapping(value = {"/product/search"})
    public List<GetProductResponse> getProductByTitle(@RequestBody SearchFilter searchFilter) {
        return mapper.listProductToResponse(service.findProductEntityToFilter(searchFilter));
    }

}