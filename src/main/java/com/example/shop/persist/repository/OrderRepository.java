package com.example.shop.persist.repository;

import com.example.shop.model.OrderProductDto;
import com.example.shop.persist.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, UUID>, JpaSpecificationExecutor<OrderEntity> {

    List<OrderEntity> findOrdersByUserId(UUID userId);

    @Query("SELECT new com.example.shop.model.OrderProductDto(op.compositeKey.productId, p.description,op.quantity,op.price) "  +
            "from   OrderedProductEntity op "  +
            "join  ProductEntity  p  ON p.id = op.compositeKey.productId " +
            "join OrderEntity o ON o.id = op.compositeKey.orderId " +
            "WHERE  op.compositeKey.orderId = :orderId AND o.user.id = :userId")
    List<OrderProductDto> findProductsByOrderId(UUID userId, UUID orderId);

    @Query("from OrderEntity o " +
            "join OrderedProductEntity op ON op.compositeKey.orderId = o.id " +
            "where op.compositeKey.productId = :productId")
    List<OrderEntity> orderEntityListByProductId(UUID productId);
}
