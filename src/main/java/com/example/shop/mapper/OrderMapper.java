package com.example.shop.mapper;

import com.example.shop.controller.request.CreateOrder;
import com.example.shop.controller.response.GetOrderResponse;
import com.example.shop.model.OrderDto;
import com.example.shop.model.ProductOrder;
import com.example.shop.persist.entity.Order;
import com.example.shop.persist.entity.ProductEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order createOrder(CreateOrder createOrder);

    ProductOrder convertEntityToOrder(ProductEntity product);

    List<OrderDto> convertEntityToDto(List<Order> productOrderList);

    List<GetOrderResponse> convertDtoToResponse(List<OrderDto> productDto);
}
