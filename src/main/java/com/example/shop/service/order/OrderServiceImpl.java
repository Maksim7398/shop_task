package com.example.shop.service.order;

import com.example.shop.controller.request.CreateOrderRequest;

import com.example.shop.controller.request.CreateOrderedProduct;
import com.example.shop.controller.request.UpdateOrderRequest;
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
import com.example.shop.persist.repository.OrderedProductEntityRepository;
import com.example.shop.persist.repository.ProductRepository;
import com.example.shop.persist.repository.UserRepository;
import com.example.shop.service.interaction.ExchangeServiceClient;
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

    private final OrderedProductEntityRepository orderedProductEntityRepository;

    private final OrderMapper orderMapper;

    private final ExchangeServiceClient exchangeServiceClient;

    @Override
    @Transactional
    public UUID save(final CreateOrderRequest request, final UUID userId) {
        final UserEntity user = userRepository
                .findById(userId).orElseThrow(() -> new UserNotFoundException("user with this ID not found"));

        final List<CreateOrderedProduct> createOrderedProductList = request.getProducts();

        final OrderEntity order = OrderEntity.builder()
                .user(user)
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .status(Status.CREATED).build();

        orderRepository.save(order);
        createOrderedProduct(order, createOrderedProductList);

        return order.getId();
    }

    @Override
    public String updateStatus(final UUID orderId, final Status status) {
        final OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("order with this ID not found"));
        if (status != null) {
            orderEntity.setStatus(status);
            orderEntity.setUpdateDate(LocalDateTime.now());
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
    @Transactional
    public UUID addProductInOrderExists(UUID orderID, UpdateOrderRequest updateOrderedRequest) {
        final OrderEntity orderEntity = orderRepository.findByIdFetchOrderedProducts(orderID).orElseThrow(()
                -> new OrderNotFoundException("такого заказа не существует"));
        if (orderEntity.getStatus() != Status.CREATED) {
            throw new OrderNotFoundException("заказ с таким статусов: " + orderEntity.getStatus() + " нельзя изменить!");
        }
        final List<CreateOrderedProduct> createOrderedProductList = updateOrderedRequest.getProducts();

        orderRepository.save(orderEntity);
        createOrderedProduct(orderEntity, createOrderedProductList);
        orderEntity.setUpdateDate(LocalDateTime.now());

        return orderEntity.getId();
    }

    @Override
    public UUID deleteOrder(UUID orderID) {
        final OrderEntity orderEntity = orderRepository.findByIdFetchOrderedProducts(orderID).orElseThrow(()
                -> new OrderNotFoundException("такого заказа не существует"));
        if (orderEntity.getStatus() == Status.DELETE) {
            throw new OrderNotFoundException("Этот заказ уже удалён: " + orderEntity.getStatus());
        }
        final List<OrderedProductEntity> orderedProductEntities = orderEntity.getOrderedProducts();

        final List<ProductEntity> productEntities = productRepository.findAllById(orderedProductEntities.stream()
                .map(o -> o.getCompositeKey().getProductId())
                .toList());

        final Map<UUID, ProductEntity> productIdToProductEntityMap = productEntities.stream()
                .collect(Collectors.toMap(ProductEntity::getId, Function.identity()));
        orderedProductEntities.forEach(ope -> {
            final ProductEntity productEntity = productIdToProductEntityMap.get(ope.getCompositeKey().getProductId());
            productEntity.setQuantity(productEntity.getQuantity() + ope.getQuantity());
        });
        updateStatus(orderID, Status.DELETE);

        return orderEntity.getId();
    }

    @Override
    @Transactional
    public Map<UUID, List<OrdersInfo>> findOrdersInfoByProducts() {
        final List<OrderedProductEntity> orderedProductEntities = orderedProductEntityRepository.findAll();
        final List<OrderEntity> orderEntities = orderRepository.findAll();
        final Map<UUID, OrderEntity> orderEntityMap = orderEntities.stream().collect(Collectors.toMap(OrderEntity::getId, Function.identity()));

        final Map<String, String> allInnByEmailMap = exchangeServiceClient.getAllInnByEmail(orderEntities.stream()
                .map(u -> u.getUser().getEmail())
                .toList());

        if (allInnByEmailMap.isEmpty()) {
            throw new UserNotFoundException("map is empty, second service not found");
        }

        return orderedProductEntities.stream()
                .map(OrderedProductEntity::getCompositeKey)
                .collect(Collectors.groupingBy(
                        CompositeKey::getProductId,
                        Collectors.mapping(
                                compositeKey -> {
                                    final OrderEntity orderEntity = orderEntityMap.get(compositeKey.getOrderId());
                                    return new OrdersInfo(
                                            orderEntity.getId(),
                                            orderEntity.getStatus(),
                                            orderEntity.getOrderPrice(),
                                            orderEntity.getCreateDate(),
                                            orderEntity.getUser().getName(),
                                            allInnByEmailMap.get(orderEntity.getUser().getEmail()));
                                },
                                Collectors.toList()
                        )
                ));
    }

    private void createOrderedProduct(final OrderEntity order,
                                      final List<CreateOrderedProduct> createOrderedProductList) {
        final List<ProductEntity> productEntities = productRepository.findAllById(createOrderedProductList.stream()
                .map(CreateOrderedProduct::getId)
                .toList());

        final Map<UUID, ProductEntity> productIdToProductEntityMap = productEntities.stream()
                .collect(Collectors.toMap(ProductEntity::getId, Function.identity()));


        final List<OrderedProductEntity> orderedProductEntitiesList = Optional.ofNullable(order.getOrderedProducts())
                .orElseGet(List::of);

        final Map<UUID, OrderedProductEntity> orderedProductEntityMap =
                orderedProductEntitiesList.stream()
                        .collect(Collectors.toMap(ope -> ope.getCompositeKey().getProductId(), Function.identity()));

        final List<OrderedProductEntity> orderedProductEntities = createOrderedProductList.stream().map(createOrderedProduct -> {
            final ProductEntity productEntity = productIdToProductEntityMap.get(createOrderedProduct.getId());

            if (productEntity.getQuantity() <= createOrderedProduct.getQuantity()) {
                throw new ProductNotFoundException("в таком количестве, нет продуктов на складе");
            }
            productEntity.setQuantity(productEntity.getQuantity() - createOrderedProduct.getQuantity());

            return Optional.ofNullable(orderedProductEntityMap.get(createOrderedProduct.getId()))
                    .map(ope -> {
                        ope.setQuantity(ope.getQuantity() + createOrderedProduct.getQuantity());
                        return ope;
                    })
                    .orElseGet(() -> OrderedProductEntity.builder()
                            .compositeKey(new CompositeKey(order.getId(), createOrderedProduct.getId()))
                            .quantity(createOrderedProduct.getQuantity())
                            .price(productEntity.getPrice()).build());

        }).toList();

        orderedProductEntityRepository.saveAll(orderedProductEntities);
        order.setOrderPrice(calculateTotalPrice(orderedProductEntities));
    }

    private BigDecimal calculateTotalPrice(List<OrderedProductEntity> orderedProductEntities) {
        return orderedProductEntities.stream()
                .map(o -> o.getPrice()
                        .multiply(BigDecimal.valueOf(o.getQuantity())))
                .reduce(BigDecimal::add).get();
    }
}
