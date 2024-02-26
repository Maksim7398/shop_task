package com.example.shop.service.order;

import com.example.shop.controller.request.CreateOrderRequest;

import com.example.shop.controller.request.CreateOrderedProduct;
import com.example.shop.exception.OrderNotFoundException;
import com.example.shop.exception.ProductNotFoundException;
import com.example.shop.exception.UserNotFoundException;
import com.example.shop.mapper.OrderMapper;
import com.example.shop.model.OrderDto;
import com.example.shop.model.OrderProductDto;
import com.example.shop.model.OrdersInfo;
import com.example.shop.model.Status;
import com.example.shop.persist.entity.CompositeKey;
import com.example.shop.persist.entity.OrderEntity;
import com.example.shop.persist.entity.OrderedProductEntity;
import com.example.shop.persist.entity.ProductEntity;
import com.example.shop.persist.entity.UserEntity;
import com.example.shop.persist.repository.OrderRepository;
import com.example.shop.persist.repository.OrderToProductRepository;
import com.example.shop.persist.repository.ProductRepository;
import com.example.shop.persist.repository.UserRepository;
import com.example.shop.service.interaction.ExchangeServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    private final OrderToProductRepository orderToProductRepository;

    private final OrderMapper orderMapper;

    private final ExchangeServiceClient exchangeServiceClient;

    @Override
    @Transactional
    public UUID save(final CreateOrderRequest request, final UUID userId) {
        final UserEntity user = userRepository
                .findById(userId).orElseThrow(() -> new UserNotFoundException("user with this ID not found"));

        final List<CreateOrderedProduct> createOrderedProductList = request.getProducts();
        final List<ProductEntity> productEntities =
                productRepository.findAllById(
                        createOrderedProductList.stream()
                                .map(CreateOrderedProduct::getId).toList()
                        );

        if (productEntities.isEmpty()){
            throw new ProductNotFoundException("Таких продуктов нет на складе");
        }

        final Map<UUID, ProductEntity> productIdToProductEntityMap = productEntities.stream()
                .collect(Collectors.toMap(ProductEntity::getId, Function.identity()));

        final OrderEntity order = OrderEntity.builder()
                .user(user)
                .createDate(LocalDateTime.now())
                .status(Status.CREATED).build();
        orderRepository.save(order);

        final List<OrderedProductEntity> orderedProductEntitiesList = createOrderedProductList.stream()
                .map(createOrderedProduct -> {
                            final ProductEntity product = productIdToProductEntityMap.get(createOrderedProduct.getId());
                            product.setQuantity(product.getQuantity() - createOrderedProduct.getQuantity());
                            return OrderedProductEntity.builder()
                                    .compositeKey(new CompositeKey(order.getId(),createOrderedProduct.getId()))
                                    .quantity( createOrderedProduct.getQuantity())
                                    .price(product.getPrice()).build();
                }).toList();
        order.setOrderedProducts(orderedProductEntitiesList);

        final BigDecimal totalPrice = orderedProductEntitiesList
                .stream().map(o -> o.getPrice().multiply(BigDecimal.valueOf(o.getQuantity()))).reduce(BigDecimal::add).get();
        order.setOrderPrice(totalPrice);

        productRepository.saveAll(productEntities);
        orderToProductRepository.saveAll(orderedProductEntitiesList);

        return order.getId();
    }

    @Override
    public String updateStatus(final UUID orderId, final Status status) {
        final OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("order with this ID not found"));
        if (status != null) {
            orderEntity.setStatus(status);
            orderRepository.save(orderEntity);
            return "Статус заказа изменён на: " + orderEntity.getStatus();
        }
        return "Статус заказа не изменён: " + orderEntity.getStatus();
    }

    @Override
    public List<OrderDto> getOrdersByUserId(final UUID userId) {
        return orderMapper.convertEntityToDto(orderRepository.findOrdersByUserId(userId));
    }

    @Override
    public List<OrderProductDto> getOrderProductsByUserIdAndOrderId(final UUID userId, final UUID orderId) {
        return orderRepository.findProductsByOrderId(userId, orderId);
    }

    @Override
    public Map<UUID, List<OrdersInfo>> orderInfoByProduct(final UUID product_id) {
        final List<UUID> orderIdByProductId = orderToProductRepository.findOrderIdByProductId(product_id);
        final List<OrderEntity> orderByProductId = orderRepository.findAllById(orderIdByProductId);
        final List<OrdersInfo> ordersInfoList = new ArrayList<>();
        orderByProductId.forEach(o -> {
            ordersInfoList.add(new OrdersInfo(o.getId(),
                    o.getStatus(),
                    o.getOrderPrice(),
                    o.getCreateDate(),
                    o.getUser().getName(),
                    Arrays.toString(exchangeServiceClient.getAllInnByEmail(List.of(o.getUser().getEmail())).toArray()))
            );
        });
        final Map<UUID, List<OrdersInfo>> map = new HashMap<>();
        map.put(product_id, ordersInfoList);
        return map;
    }
}
