package com.example.shop.service.user;

import com.example.shop.controller.request.CreateUserRequest;
import com.example.shop.persist.entity.UserEntity;

public interface UserService {

    UserEntity createUser(CreateUserRequest user);

}
