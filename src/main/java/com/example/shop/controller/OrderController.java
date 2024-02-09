package com.example.shop.controller;


import com.example.shop.controller.request.CreateOrderRequest;
import com.example.shop.controller.response.GetOrderResponse;
import com.example.shop.mapper.OrderMapper;
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
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    private final OrderMapper orderMapper;

    @PostMapping("/order")
    public UUID createOrder(@RequestBody @Valid CreateOrderRequest createOrderRequest) {
        return service.save(createOrderRequest);
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



}
