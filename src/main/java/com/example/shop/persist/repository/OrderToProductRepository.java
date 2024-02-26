package com.example.shop.persist.repository;

import com.example.shop.persist.entity.CompositeKey;
import com.example.shop.persist.entity.OrderedProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderToProductRepository extends JpaRepository<OrderedProductEntity, CompositeKey> {

}
