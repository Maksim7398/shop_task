package com.example.shop.controller.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserRequest {
    @NotEmpty
    private String name;
    @NotEmpty
    private String email;
}
