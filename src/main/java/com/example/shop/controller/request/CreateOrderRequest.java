package com.example.shop.controller.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class CreateOrderRequest {
    @NotEmpty
    private List<CreateOrderedProduct> products;
}
