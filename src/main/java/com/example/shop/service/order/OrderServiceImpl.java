package com.example.shop.service.order;

import com.example.shop.controller.request.CreateOrderRequest;

import com.example.shop.controller.request.CreateOrderedProduct;
import com.example.shop.exception.OrderNotFoundException;
import com.example.shop.exception.ProductNotFoundException;
import com.example.shop.exception.UserNotFoundException;
import com.example.shop.mapper.OrderMapper;
import com.example.shop.model.OrderDto;
import com.example.shop.model.OrderProductDto;
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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import java.util.Map;
import java.util.Optional;
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

    @Override
    @Transactional
    public UUID save(final CreateOrderRequest request, final UUID user_id) {
        final UserEntity user = userRepository
                .findById(user_id).orElseThrow(() -> new UserNotFoundException("user with this ID not found"));

        final List<CreateOrderedProduct> createOrderedProductList = request.getProducts();
        final List<ProductEntity> productEntities =
                productRepository.findAllById(
                        createOrderedProductList.stream()
                                .map(CreateOrderedProduct::getId).toList()
                );

        if (productEntities.isEmpty()) {
            throw new ProductNotFoundException("Таких продуктов нет на складе");
        }

        final OrderEntity order = OrderEntity.builder()
                .user(user)
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .status(Status.CREATED).build();

        orderRepository.save(order);
        createOrderedProduct(productEntities, order, createOrderedProductList);
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
    public List<OrderDto> getOrdersByUserId(final UUID uuid) {
        return orderMapper.convertEntityToDto(orderRepository.findOrdersByUserId(uuid));
    }

    @Override
    public List<OrderProductDto> getOrderProductsByUserIdAndOrderId(final UUID userId, final UUID orderId) {
        return orderRepository.findProductsByOrderId(userId, orderId);
    }

    @Override
    @Transactional
    public void addProductInOrderExists(UUID orderID, CreateOrderRequest createOrderedRequest) {
        final OrderEntity orderEntity = orderRepository.findById(orderID).orElseThrow(()
                -> new OrderNotFoundException("такого заказа не существует"));
        if (orderEntity.getStatus() != Status.CREATED) {
            throw new OrderNotFoundException("заказ с таким статусов: " + orderEntity.getStatus() + " нельзя изменить!");
        }
        final List<ProductEntity> productEntities = productRepository.findAllById(createOrderedRequest.getProducts().stream()
                .map(CreateOrderedProduct::getId)
                .toList());

        final List<CreateOrderedProduct> createOrderedProductList = createOrderedRequest.getProducts();
        orderRepository.save(orderEntity);
        createOrderedProduct(productEntities, orderEntity, createOrderedProductList);
        orderEntity.setUpdateDate(LocalDateTime.now());
    }

    @Override
    public BigDecimal calculateTotalPrice(List<OrderedProductEntity> entities) {
        return entities.stream()
                .map(o -> o.getPrice()
                        .multiply(BigDecimal.valueOf(o.getQuantity())))
                .reduce(BigDecimal::add).get();
    }

    @Transactional
    @Override
    public void createOrderedProduct(List<ProductEntity> productEntities,
                                     OrderEntity order,
                                     List<CreateOrderedProduct> createOrderedProductList) {
        final Map<UUID, ProductEntity> productIdToProductEntityMap = productEntities.stream()
                .collect(Collectors.toMap(ProductEntity::getId, Function.identity()));

        createOrderedProductList.forEach(createOrderedProduct -> {
            final ProductEntity product = productIdToProductEntityMap.get(createOrderedProduct.getId());
            product.setQuantity(
                    Optional.of(product.getQuantity() - createOrderedProduct.getQuantity())
                            .filter(p -> !(product.getQuantity() <= createOrderedProduct.getQuantity())).orElseThrow(() ->
                                    new ProductNotFoundException("в таком количестве, нет продуктов на складе")));
        });

        if (order.getOrderedProducts() != null) {
            order.getOrderedProducts().forEach(o -> {
                createOrderedProductList.stream()
                        .filter(c -> c.getId().equals(o.getCompositeKey().getProductId()))
                        .forEach(v -> v.setQuantity(o.getQuantity() + v.getQuantity()));
            });
        }

        final List<OrderedProductEntity> orderedProductEntities = createOrderedProductList.stream()
                .map(createOrderedProduct -> {
                    final ProductEntity productEntity = productIdToProductEntityMap.get(createOrderedProduct.getId());
                    return OrderedProductEntity.builder()
                            .compositeKey(new CompositeKey(order.getId(), createOrderedProduct.getId()))
                            .quantity(createOrderedProduct.getQuantity())
                            .price(productEntity.getPrice()).build();
                }).toList();

        order.setOrderPrice(calculateTotalPrice(orderedProductEntities));
        orderToProductRepository.saveAll(orderedProductEntities);
    }
}
