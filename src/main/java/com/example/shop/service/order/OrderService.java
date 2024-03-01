package com.example.shop.service.order;

import com.example.shop.controller.request.CreateOrderRequest;
import com.example.shop.controller.request.CreateOrderedProduct;
import com.example.shop.model.OrderDto;
import com.example.shop.model.OrderProductDto;
import com.example.shop.model.Status;
import com.example.shop.persist.entity.OrderEntity;

import java.math.BigDecimal;
import java.util.List;

import com.example.shop.model.OrdersInfo;

import java.util.Map;
import java.util.UUID;

public interface OrderService {

    UUID save(CreateOrderRequest request, UUID userId);

    String updateStatus(UUID orderId, Status status);

    List<OrderDto> getOrdersByUserId(UUID userId);

    List<OrderProductDto> getOrderProductsByUserIdAndOrderId(UUID userId, UUID orderId);

    BigDecimal calculateTotalPrice(OrderEntity orderEntity);

    void createOrderedProduct(OrderEntity order, List<CreateOrderedProduct> createOrderedProductList);

    void addProductInOrderExists(UUID orderID, CreateOrderRequest createOrderRequest);

    Map<UUID, List<OrdersInfo>> findOrdersInfoByProducts();
}
