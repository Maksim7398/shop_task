package com.example.shop.controller;

import com.example.shop.controller.request.CreateUserRequest;
import com.example.shop.persist.entity.UserEntity;
import com.example.shop.service.interaction.ExchangeServiceClient;
import com.example.shop.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final ExchangeServiceClient exchangeServiceClient;

    @PostMapping("/user")
    public UUID createUser(@RequestBody CreateUserRequest createUserRequest) {
        final UserEntity createUserEntity = userService.createUser(createUserRequest);
        return createUserEntity.getId();
    }

    @PostMapping("/user/inn_by_email")
    private List getInnByEmail(@RequestBody List<String> email){
        return exchangeServiceClient.getAllInnByEmail(email);
    }
}
