package com.example.shop.controller;

import com.example.shop.controller.request.CreateUserRequest;
import com.example.shop.persist.entity.User;
import com.example.shop.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/user")
    public UUID createUser(@RequestBody CreateUserRequest createUserRequest) {
        final User save = userService.save(createUserRequest);
        return save.getId();
    }


}
