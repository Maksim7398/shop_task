package com.example.shop.persist.repository;

import com.example.shop.model.OrderProductDto;
import com.example.shop.persist.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, UUID>, JpaSpecificationExecutor<OrderEntity> {

    List<OrderEntity> findOrderByUserId(UUID id);

    @Query("SELECT new com.example.shop.model.OrderProductDto(op.compositeKey.productId, p.description,op.quantity,op.price) "  +
            "from   OrderedProductEntity op "  +
            "join  ProductEntity  p  ON p.id = op.compositeKey.productId " +
            "join OrderEntity o ON o.id = op.compositeKey.orderId " +
            "WHERE  op.compositeKey.orderId = :order_id AND o.user.id = :user_id")
    List<OrderProductDto> findProductsByOrderId(@Param("user_id") UUID user_id, @Param("order_id") UUID order_id);
}
