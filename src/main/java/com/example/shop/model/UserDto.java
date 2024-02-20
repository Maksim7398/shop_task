package com.example.shop.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserDto {

    private UUID id;

    private String name;

    private String email;
}
