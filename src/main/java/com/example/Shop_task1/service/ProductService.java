package com.example.Shop_task1.service;

import com.example.Shop_task1.data.Categories;
import com.example.Shop_task1.data.ProductRepository;
import com.example.Shop_task1.data.Product;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.SessionLazyDelegator;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;


@Service
@RequiredArgsConstructor
public class ProductService {


    private final ProductRepository repository;



    Logger logger = Logger.getLogger(ProductService.class.getSimpleName());

    public void save(Product product){
        repository.save(product);
    }
    public Product getProductById(String id){
        Optional<Product> byId = repository.findById(UUID.fromString(id));
        return byId.get();
    }

    public void deleteProdById(String id){
        repository.deleteById(UUID.fromString(id));
        logger.info("delete by: " + id);
    }
    public void updateProduct(String id, Product p){
        Product product = getProductById(id);
        product.setTitle(p.getTitle());
        product.setCount(p.getCount());
        product.setCategories(p.getCategories());
        product.setExchangeCount(p.getExchangeCount());
        product.setDescription(p.getDescription());
        product.setPrice(p.getPrice());
        save(product);
    }

}
