package com.example.shop.controller.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;
@Data
public class UpdateOrderRequest {
    @NotEmpty
    private final List<CreateOrderedProduct> products;
}
