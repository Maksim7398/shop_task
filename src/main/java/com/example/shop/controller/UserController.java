package com.example.shop.controller;

import com.example.shop.controller.request.CreateUserRequest;
import com.example.shop.controller.response.GetOrderProductResponse;
import com.example.shop.mapper.OrderMapper;
import com.example.shop.service.order.OrderService;
import com.example.shop.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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

    private final OrderMapper orderMapper;

    private final OrderService orderService;

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
     * Этот метод используется для получения всех продуктов которые есть в заказе у пользователя
     *
     * @param userId  - id пользователя по которому будут полученны продукты
     * @param orderId - id заказа в котором созедржать продукты
     * @return возращает продукты которые есть в заказе у пользователя
     */
    @GetMapping("/{userId}/{orderId}")
    public List<GetOrderProductResponse> getOrdersProductByUserId(@PathVariable UUID userId, @PathVariable UUID orderId) {
        return orderMapper.convertListProductsOrderDtoToResponse(orderService.getOrderProductByUserId(userId, orderId));
    }
}
