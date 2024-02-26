package com.example.shop.service.order;

import com.example.shop.controller.request.CreateOrderRequest;
import com.example.shop.model.OrderDto;
import com.example.shop.model.OrderProductDto;
import com.example.shop.model.Status;

import java.util.List;
import com.example.shop.model.OrdersInfo;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

public interface OrderService {

    UUID save(CreateOrderRequest request,UUID user_id);

    String updateStatus(UUID orderId, Status status);

    List<OrderDto>  getOrdersByUserId(UUID uuid);

    List<OrderProductDto> getOrderProductsByUserIdAndOrderId(UUID userId, UUID orderId);

    Map<UUID, Set<OrdersInfo>> findOrdersInfoByProducts();
}
