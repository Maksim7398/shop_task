package com.example.shop.service.order;

import com.example.shop.controller.request.CreateOrder;
import com.example.shop.controller.request.CreateOrderRequest;

import com.example.shop.mapper.OrderMapper;
import com.example.shop.model.OrderDto;
import com.example.shop.model.Status;
import com.example.shop.persist.entity.CompositeKey;
import com.example.shop.persist.entity.Order;
import com.example.shop.persist.entity.OrderToProduct;
import com.example.shop.persist.entity.ProductEntity;
import com.example.shop.persist.repository.OrderRepository;
import com.example.shop.persist.repository.OrderToProductRepository;
import com.example.shop.persist.repository.ProductRepository;
import com.example.shop.persist.repository.UserRepository;
import com.example.shop.service.product.ProductServiceImpl;
import com.example.shop.service.product.request.ImmutableUpdateProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    private final OrderToProductRepository orderToProductRepository;

    private final ProductServiceImpl productService;

    private final OrderMapper orderMapper;

    @Override
    public UUID save(CreateOrderRequest request) {
        final List<CreateOrder> createOrderList = request.getProduct();
        final List<ProductEntity> productOrders = new ArrayList<>();
        final OrderToProduct orderToProduct = new OrderToProduct();
        final CompositeKey compositeKey = new CompositeKey();
        final double[] totalPrice = new double[3];

        for (CreateOrder createOrder : createOrderList) {
            final ProductEntity product = productRepository.findById(createOrder.getId()).get();
            productOrders.add(product);
            productService.updateProduct(product.getId(), ImmutableUpdateProductRequest.builder()
                    .price(product.getPrice())
                    .article(product.getArticle())
                    .title(product.getTitle())
                    .description(product.getDescription())
                    .isAvailable(product.getIsAvailable())
                    .category(product.getCategory())
                    .quantity((product.getQuantity() - createOrder.getQuantity()))
                    .build());

            totalPrice[1] = product.getPrice().doubleValue() * createOrder.getQuantity().doubleValue();
            totalPrice[0] += totalPrice[1];
        }


        final Order order = Order.builder()
                .user(userRepository.findById(request.getUser()).get())
                .product(productOrders)
                .createDate(LocalDateTime.now())
                .status(Status.CREATED).build();

        order.setOrderPrice(BigDecimal.valueOf(totalPrice[0]));
        final Order saveOrder = orderRepository.save(order);
        final UUID id = saveOrder.getId();
        createOrderList.forEach((o) -> {
            compositeKey.setProductId(o.getId());
            compositeKey.setOrderId(id);
            orderToProduct.setQuantity(o.getQuantity());
            orderToProduct.setOrderId(compositeKey);
            orderToProductRepository.save(orderToProduct);
        });
        return id;
    }

    @Override
    public String updateStatus(UUID orderId, Status status) {
        final Order order = orderRepository.findById(orderId).get();
        if (status != null) {
            order.setStatus(status);
            orderRepository.save(order);
            return "Статус заказа изменён на: " + order.getStatus();
        }
        return "Статус заказа не изменён: " + order.getStatus();
    }

    @Override
    public List<OrderDto> getOrdersByUserId(UUID uuid) {
        return (orderMapper.convertEntityToDto(orderRepository.findOrderByUserId(uuid)));
    }
}
