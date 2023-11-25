package com.example.Shop_task1.service;

import com.example.Shop_task1.data.CRUDRepository;
import com.example.Shop_task1.data.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final CRUDRepository repository;

    public void save(Product product){
        repository.save(product);
    }

}
