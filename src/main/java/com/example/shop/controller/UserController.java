package com.example.shop.controller;

import com.example.shop.controller.request.CreateUserRequest;
import com.example.shop.persist.entity.UserEntity;
import com.example.shop.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        final UserEntity createUserEntity = userService.createUser(createUserRequest);
        return createUserEntity.getId();
    }
    @GetMapping("/user/{user_id}/{news_id}")
    public String watchNews(@PathVariable UUID user_id, @PathVariable UUID news_id){
        userService.watchNews(user_id,news_id);
        return "Новость " + news_id + " просмотрена";
    }

    @DeleteMapping("/user/{user_id}/{news_id}")
    public String unwatchNews(@PathVariable UUID user_id, @PathVariable UUID news_id){
        userService.unwatchNews(user_id,news_id);
        return "Новость " + news_id + " не просмотрена";
    }

}