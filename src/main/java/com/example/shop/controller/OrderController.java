package com.example.shop.controller;


import com.example.shop.controller.request.CreateOrderRequest;
import com.example.shop.controller.response.GetOrderProductResponse;
import com.example.shop.controller.response.GetOrderResponse;
import com.example.shop.mapper.OrderMapper;
import com.example.shop.model.OrdersInfo;
import com.example.shop.model.Status;
import com.example.shop.service.order.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    private final OrderMapper orderMapper;

    @PostMapping("/order/{user_id}")
    public UUID createOrder(@RequestBody @Valid CreateOrderRequest createOrderRequest,@PathVariable UUID user_id) {
        return service.save(createOrderRequest,user_id);
    }

    @PutMapping("/order/{id}")
    public String changeStatus(@RequestBody String status, @PathVariable UUID id){
        final Status statusChange = Status.fromName(status);
        return service.updateStatus(id, statusChange);
    }

    @GetMapping("/order/{id}")
    public List<GetOrderResponse> getOrdersByUserId(@PathVariable UUID id){
       return orderMapper.convertDtoToResponse(service.getOrdersByUserId(id));
    }

    @GetMapping("/order/product/{user_id}/{order_id}")
    public List<GetOrderProductResponse> getOrdersProductByUserId(@PathVariable UUID user_id,@PathVariable UUID order_id){
        return orderMapper.convertListProductsOrderDtoToResponse(service.getOrderProductByUserId(user_id,order_id));
    }

    @GetMapping("/order/product/{product_id}")
    public Map<UUID,List<OrdersInfo>> mapOrderToProduct(@PathVariable UUID product_id){
      return service.orderInfoByProduct(product_id);
    }

}
