package com.example.shop.controller;

import com.example.shop.controller.request.CreateOrderRequest;
import com.example.shop.controller.request.UpdateOrderRequest;
import com.example.shop.controller.response.GetOrderProductResponse;
import com.example.shop.controller.response.GetOrderResponse;
import com.example.shop.mapper.OrderMapper;
import com.example.shop.model.OrdersInfo;
import com.example.shop.model.Status;
import com.example.shop.service.order.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Класс контроллера заказов
 *
 * @author Maksim7398
 */
@RestController
@RequiredArgsConstructor
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
    @PostMapping("/order/{userId}")
    public UUID createOrder(@RequestBody @Valid CreateOrderRequest createOrderRequest, @PathVariable UUID userId) {
        return service.save(createOrderRequest, userId);
    }

    /**
     * Этот метод используется для изменения статуса заказа
     *
     * @param status  - новый статус заказа
     * @param orderId - id заказа в котором будет изменён статус
     * @return возращает информацию о том изменён статус заказа или нет
     */
    @PatchMapping("/order/status/{orderId}")
    public String changeStatus(@RequestBody Status status, @PathVariable UUID orderId) {
        return service.updateStatus(orderId, status);
    }

    /**
     * Этот метод используется для получения всех продуктов которые есть в заказе у пользователя
     *
     * @param userId  - id пользователя по которому будут полученны продукты
     * @param orderId - id заказа в котором созедржать продукты
     * @return возращает продукты которые есть в заказе у пользователя
     */
    @GetMapping("/order/{orderId}")
    public List<GetOrderProductResponse> getOrderProductsById(@RequestParam("userId") UUID userId, @PathVariable UUID orderId) {
        return orderMapper.convertListProductsOrderDtoToResponse(service.getOrderProductsByUserIdAndOrderId(userId, orderId));
    }

    /**
     * Этот метод используется для обавления товаров в заказ
     *
     * @param updateOrderRequest  - сущность обновления заказа
     * @param orderId - id заказа в который будет обновлён
     * @return возращает айди заказа
     */
    @PatchMapping("/order/{orderId}")
    public UUID addProductInOrderExists(@RequestBody @Valid UpdateOrderRequest updateOrderRequest, @PathVariable UUID orderId) {
        return service.addProductInOrderExists(orderId,updateOrderRequest);
    }

    /**
     * Этот метод используется для получения информации о заказах по заказанным продуктам
     *
     * @return возращает мапу с id продукта и информации о заказах в которых есть этот продукт
     */
    @GetMapping("/orders")
    public Map<UUID, List<OrdersInfo>> findOrdersInfoByProducts() {
        return service.findOrdersInfoByProducts();
    }

    /**
     * Этот метод возращает все заказы которые есть у пользователя
     *
     * @param userId - id пользователя по которому будут полученны заказы
     * @return возращает заказы которые есть у пользователя
     */
    @GetMapping("/orders/{userId}")
    public List<GetOrderResponse> getOrdersByUserId(@PathVariable UUID userId) {
        return orderMapper.convertDtoToResponse(service.getOrdersByUserId(userId));
    }

    /**
     * метод используется для удаления заказа
     *
     * @param orderId - id заказа по коорому будет удаление
     * @return возращает айди заказа который удалён
     */
    @DeleteMapping("/order/{orderId}")
    public UUID deleteOrder(@PathVariable UUID orderId){
        return service.deleteOrder(orderId);
    }
}
