package com.example.shop.service.user;

import com.example.shop.controller.request.CreateUserRequest;
import com.example.shop.model.UserDto;
import java.util.UUID;

public interface UserService {

    UserDto createUser(CreateUserRequest user);

    void watchNews(UUID user_id, UUID news_id);

    void unwatchNews(UUID user_id, UUID news_id);
}

