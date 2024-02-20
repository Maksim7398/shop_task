package com.example.shop.controller;

import com.example.shop.controller.request.CreateProductRequest;
import com.example.shop.controller.request.SearchFilter;
import com.example.shop.controller.request.UpdateProductRequest;
import com.example.shop.controller.response.GetProductResponse;
import com.example.shop.controller.response.UpdateProductResponse;
import com.example.shop.exception.ProductNotFoundException;
import com.example.shop.mapper.ProductMapper;
import com.example.shop.service.product.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Класс контроллера продуктов
 *
 * @author Maksim7398
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;
    private final ProductMapper mapper;

    /**
     * Этот метод используется для получения всех продуктов
     *
     * @param pageable - разбивает информацию на страницы
     * @return возвращает лист всех продуктов
     */
    @GetMapping("/products")
    public List<GetProductResponse> listProducts(Pageable pageable) {
        return mapper.listProductToResponse(service.productList(pageable));
    }

    /**
     * Этот метод используется для создания всех продуктов
     *
     * @param createProductRequest - cущность для создания продукта
     * @return возвращает uuid созданого продукта
     */
    @PostMapping("/product")
    public UUID createProduct(@RequestBody @Valid CreateProductRequest createProductRequest) {
        return service.save(createProductRequest);
    }

    /**
     * Этот метод получает продукт по id
     *
     * @param id idпо которому надо будет получить продукт
     * @return возвращает продукт по заданному id
     */
    @GetMapping("/product/{id}")
    public GetProductResponse getProductById(@PathVariable UUID id) {
        return mapper.convertFromDto(service.getProductById(id));
    }

    /**
     * Этот метод используется длядаления продукта по id
     *
     * @param id id продукта который будет удалён
     * @return статус выполненной операции
     */
    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteByID(@PathVariable UUID id) {
        try {
            service.deleteProductById(id);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ProductNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    /**
     * Этот метод используется для изменения продукта по id
     *
     * @param product изменённый продукт
     * @param id      id продукта который будет изменён
     * @return возвращает изменённый продукт
     */
    @PutMapping("/product/{id}")
    public UpdateProductResponse updateProduct(@RequestBody UpdateProductRequest product, @PathVariable UUID id) {
        return mapper.convertFromDtoToResponse(service.updateProduct(id, mapper.convertFromUpdateToImmutableRequest(product)));
    }

    /**
     * Этот метод используется для поиска продукта через фильтр
     *
     * @param searchFilter - сущность фильтра по полям которого будет осущевстляться поиск
     * @return возвращает продукты которые подходят критериям филтра
     */
    @GetMapping(value = {"/product/search"})
    public List<GetProductResponse> getProductByTitle(@RequestBody SearchFilter searchFilter) {
        return mapper.listProductToResponse(service.findProductEntityToFilter(searchFilter));
    }
}