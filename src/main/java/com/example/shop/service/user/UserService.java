package com.example.shop.service.user;

import com.example.shop.controller.request.CreateUserRequest;
import com.example.shop.model.UserDto;

public interface UserService {
    UserDto createUser(CreateUserRequest user);
}
