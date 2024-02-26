package com.example.shop.controller;

import com.example.shop.controller.request.CreateUserRequest;
import com.example.shop.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Класс контроллера пользователя
 *
 * @author Maksim7398
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    /**
     * Этот метод используется для создания пользователя
     *
     * @param createUserRequest сущность создания пользователя
     * @return возращает uuid созданого пользователя
     */
    @PostMapping
    public UUID createUser(@RequestBody CreateUserRequest createUserRequest) {
        return userService.createUser(createUserRequest).getId();
    }

    /**
     * Этот метод получает заказы по id пользователя
     *
     * @param userId - id пользователя по которому нужно получить заказы
     * @return все заказы которые есть у пользователя
     */
    @GetMapping("/{userId}")
    public List<GetOrderResponse> getOrdersByUserId(@PathVariable UUID userId) {
        return orderMapper.convertDtoToResponse(orderService.getOrdersByUserId(userId));
    }

    @GetMapping("/{user_id}/{news_id}")
    public String watchNews(@PathVariable UUID user_id, @PathVariable UUID news_id){
        userService.watchNews(user_id,news_id);
        return "Новость " + news_id + " просмотрена";
    }

    @DeleteMapping("/{user_id}/{news_id}")
    public String unwatchNews(@PathVariable UUID user_id, @PathVariable UUID news_id){
        userService.unwatchNews(user_id,news_id);
        return "Новость " + news_id + " не просмотрена";
    }
}