package com.example.shop.controller.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserRequest {

    private String name;
    private String email;

}
