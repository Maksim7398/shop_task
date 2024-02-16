package com.example.shop.persist.repository;

import com.example.shop.persist.entity.CompositeKey;
import com.example.shop.persist.entity.OrderedProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderToProductRepository extends JpaRepository<OrderedProductEntity, CompositeKey> {

    @Query("select ope.compositeKey.orderId from OrderedProductEntity ope " +
            "where ope.compositeKey.productId = :productId")
    List<UUID> findOrderIdByProductId(@Param("productId") UUID productId);

}
