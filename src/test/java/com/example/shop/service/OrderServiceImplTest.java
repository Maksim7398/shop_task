package com.example.shop.service;

import com.example.shop.model.OrderBuilder;
import com.example.shop.model.OrderedProductsEntityBuilder;
import com.example.shop.model.OrdersInfo;
import com.example.shop.model.ProductEntityBuilder;
import com.example.shop.model.Status;
import com.example.shop.model.UserBuilder;
import com.example.shop.persist.entity.CompositeKey;
import com.example.shop.persist.entity.OrderEntity;
import com.example.shop.persist.entity.OrderedProductEntity;
import com.example.shop.persist.entity.ProductEntity;
import com.example.shop.persist.entity.UserEntity;
import com.example.shop.persist.repository.OrderRepository;
import com.example.shop.persist.repository.OrderedProductEntityRepository;
import com.example.shop.service.interaction.ExchangeServiceClient;
import com.example.shop.service.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {
    @Mock
    private OrderRepository orderRepositoryMock;
    @Mock
    private OrderedProductEntityRepository orderedProductEntityRepository;
    @Mock
    private ExchangeServiceClient exchangeServiceClient;
    @InjectMocks
    private OrderServiceImpl orderServiceMock;

    @Test
    public void orderInfoByProduct_test() {
        final ProductEntity productEntity = ProductEntityBuilder.aProductEntity().build();
        final ProductEntity productEntity2 = ProductEntityBuilder.aProductEntity().withId(UUID.randomUUID()).build();
        final UserEntity userEntity = UserBuilder.aUserEntity().build();
        final OrderEntity orderEntity = OrderBuilder.aOrderEntity().build();
        final OrderEntity orderEntity2 = OrderBuilder.aOrderEntity()
                .withID(UUID.randomUUID())
                .withPrice(new BigDecimal("1000"))
                .withOrderedProduct(List.of(OrderedProductEntity.builder().build()))
                .withCreateDate(LocalDateTime.now())
                .withUser(UserEntity.builder().id(UUID.randomUUID()).email("user2@mail.ru").name("user2").build())
                .withStatus(Status.REJECTED)
                .build();

        final OrderedProductEntity orderedProductEntity = OrderedProductsEntityBuilder.aOrderedProductEntity().build();
        final OrderedProductEntity orderedProductEntity2 = OrderedProductEntity.builder()
                .compositeKey(new CompositeKey(orderEntity2.getId(), productEntity.getId()))
                .price(new BigDecimal("100"))
                .quantity(50)
                .build();
        final OrderedProductEntity orderedProductEntity3 = OrderedProductEntity.builder()
                .compositeKey(new CompositeKey(orderEntity2.getId(), productEntity2.getId()))
                .price(new BigDecimal("100"))
                .quantity(50)
                .build();
        final Map<String, String> mapEmailOnInn = new HashMap<>();
        mapEmailOnInn.put(userEntity.getEmail(), "111111");
        mapEmailOnInn.put(orderEntity2.getUser().getEmail(), "222222");

        when(orderedProductEntityRepository.findAll()).thenReturn(List.of(orderedProductEntity, orderedProductEntity2, orderedProductEntity3));
        when(orderRepositoryMock.findAll()).thenReturn(List.of(orderEntity, orderEntity2));
        when(exchangeServiceClient.getAllInnByEmail(anyList())).thenReturn(mapEmailOnInn);

        final Map<UUID, List<OrdersInfo>> actual = orderServiceMock.findOrdersInfoByProducts();

        verify(exchangeServiceClient).getAllInnByEmail(anyList());

        assertThat(actual.get(productEntity.getId()))
                .anySatisfy(ordersInfo ->
                {
                    assertEquals(orderEntity.getStatus(), ordersInfo.getStatus());
                    assertEquals(userEntity.getName(), ordersInfo.getUserName());
                    assertEquals(mapEmailOnInn.get(userEntity.getEmail()), ordersInfo.getUserInn());
                })
                .anySatisfy(ordersInfo ->
                {
                    assertEquals(orderEntity2.getStatus(), ordersInfo.getStatus());
                    assertEquals(orderEntity2.getUser().getName(), ordersInfo.getUserName());
                    assertEquals(mapEmailOnInn.get(orderEntity2.getUser().getEmail()), ordersInfo.getUserInn());
                });

        assertThat(actual.get(productEntity2.getId()))
                .anySatisfy(ordersInfo ->
                {
                    assertEquals(orderEntity2.getStatus(), ordersInfo.getStatus());
                    assertEquals(orderEntity2.getUser().getName(), ordersInfo.getUserName());
                    assertEquals(mapEmailOnInn.get(orderEntity2.getUser().getEmail()), ordersInfo.getUserInn());
                });
    }
}
