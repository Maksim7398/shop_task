package com.example.shop.service.order;

import com.example.shop.controller.request.CreateOrderRequest;
import com.example.shop.controller.request.CreateOrderedProduct;
import com.example.shop.model.OrderDto;
import com.example.shop.model.OrderProductDto;
import com.example.shop.model.Status;
import com.example.shop.persist.entity.OrderEntity;
import com.example.shop.persist.entity.OrderedProductEntity;
import com.example.shop.persist.entity.ProductEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface OrderService {

    UUID save(CreateOrderRequest request, UUID user_id);
    String updateStatus(UUID orderId, Status status);
    List<OrderDto> getOrdersByUserId(UUID uuid);

    List<OrderProductDto> getOrderProductsByUserIdAndOrderId(UUID userId, UUID orderId);

    BigDecimal calculateTotalPrice(List<OrderedProductEntity> entities);
    void createOrderedProduct(List<ProductEntity> productEntities,
                              OrderEntity order,
                              List<CreateOrderedProduct> createOrderedProductList);
    void addProductInOrderExists(UUID orderID, CreateOrderRequest createOrderRequest);
}
