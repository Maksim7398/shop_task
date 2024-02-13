package com.example.shop.mapper;

import com.example.shop.controller.response.GetOrderProductResponse;
import com.example.shop.controller.response.GetOrderResponse;
import com.example.shop.model.OrderDto;
import com.example.shop.model.OrderProductDto;
import com.example.shop.persist.entity.OrderEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    List<GetOrderProductResponse> convertListProductsOrderDtoToResponse(List<OrderProductDto> orderProductDtoList);

    List<OrderDto> convertEntityToDto(List<OrderEntity> productOrderListEntity);

    List<GetOrderResponse> convertDtoToResponse(List<OrderDto> productDto);
}
