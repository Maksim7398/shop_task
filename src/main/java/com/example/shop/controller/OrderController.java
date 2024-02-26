package com.example.shop.controller;


import com.example.shop.controller.request.CreateOrderRequest;
import com.example.shop.controller.response.GetOrderProductResponse;
import com.example.shop.controller.response.GetOrderResponse;
import com.example.shop.mapper.OrderMapper;
import com.example.shop.model.OrdersInfo;
import com.example.shop.model.Status;
import com.example.shop.service.order.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Класс контроллера заказов
 *
 * @author Maksim7398
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService service;

    private final OrderMapper orderMapper;

    /**
     * Этот метод используется для создания заказа
     *
     * @param createOrderRequest - сущность создания заказа
     * @param userId             - id пользователя который создаёт заказ
     * @return uuid зоданого заказа
     */
    @PostMapping("/{userId}")
    public UUID createOrder(@RequestBody @Valid CreateOrderRequest createOrderRequest, @PathVariable UUID userId) {
        return service.save(createOrderRequest, userId);
    }

    /**
     * Этот метод используется для изменения статуса заказа
     *
     * @param status - новый статус заказа
     * @param id     - id заказа в котором будет изменён статус
     * @return возращает информацию о том изменён статус заказа или нет
     */
    @PatchMapping("/{id}")
    public String changeStatus(@RequestBody Status status, @PathVariable UUID id) {
        return service.updateStatus(id, status);
    }

    /**
     * Этот метод используется для получения всех продуктов которые есть в заказе у пользователя
     *
     * @param userId  - id пользователя по которому будут полученны продукты
     * @param orderId - id заказа в котором созедржать продукты
     * @return возращает продукты которые есть в заказе у пользователя
     */
    @GetMapping("/{orderId}")
    public List<GetOrderProductResponse> getOrderProductsById(@RequestParam("userId") UUID userId, @PathVariable UUID orderId) {
        return orderMapper.convertListProductsOrderDtoToResponse(service.getOrderProductsByUserIdAndOrderId(userId, orderId));
    }

    /**
     * Этот метод используется для получения информации о заказах по заказанным продуктам
     *
     * @return возращает мапу с id продукта и информации о заказах в которых есть этот продукт
     */
    @GetMapping("/orders_info")
    public Map<UUID, Set<OrdersInfo>> findOrdersInfoByProducts(){
        return service.orderInfoByProduct();
    }

    /**
     * Этот метод возращает все заказы которые есть у пользователя
     *
     * @param userId  - id пользователя по которому будут полученны заказы
     * @return возращает заказы которые есть у пользователя
     */
    @GetMapping("/{userId}")
    public List<GetOrderResponse> getOrdersByUserId(@PathVariable UUID userId){
        return orderMapper.convertDtoToResponse(service.getOrdersByUserId(userId));
    }
}
