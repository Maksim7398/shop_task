package com.example.shop.service.user;

import com.example.shop.controller.request.CreateUserRequest;
import com.example.shop.persist.entity.User;

public interface UserService {
    User save(CreateUserRequest user);

}
