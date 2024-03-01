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
    private OrderedProductEntityRepository orderedProductEntityRepositoryMock;
    @Mock
    private ExchangeServiceClient exchangeServiceClientMock;
    @InjectMocks
    private OrderServiceImpl underTest;

    @Test
    public void orderInfoByProduct_test() {
        final ProductEntity productEntityStub = ProductEntityBuilder.aProductEntity().build();
        final ProductEntity productEntityStub2 = ProductEntityBuilder.aProductEntity().withId(UUID.randomUUID()).build();
        final UserEntity userEntityStub = UserBuilder.aUserEntity().build();
        final OrderEntity orderEntityStub = OrderBuilder.aOrderEntity().build();
        final OrderEntity orderEntityStub2 = OrderBuilder.aOrderEntity()
                .withID(UUID.randomUUID())
                .withPrice(new BigDecimal("1000"))
                .withOrderedProduct(List.of(OrderedProductEntity.builder().build()))
                .withCreateDate(LocalDateTime.now())
                .withUser(UserEntity.builder().id(UUID.randomUUID()).email("user2@mail.ru").name("user2").build())
                .withStatus(Status.REJECTED)
                .build();

        final OrderedProductEntity orderedProductEntityStub = OrderedProductsEntityBuilder.aOrderedProductEntity().build();
        final OrderedProductEntity orderedProductEntityStub2 = OrderedProductEntity.builder()
                .compositeKey(new CompositeKey(orderEntityStub2.getId(), productEntityStub.getId()))
                .price(new BigDecimal("100"))
                .quantity(50)
                .build();
        final OrderedProductEntity orderedProductEntity3 = OrderedProductEntity.builder()
                .compositeKey(new CompositeKey(orderEntityStub2.getId(), productEntityStub2.getId()))
                .price(new BigDecimal("100"))
                .quantity(50)
                .build();
        final Map<String, String> mapEmailOnInnStub = new HashMap<>();
        mapEmailOnInnStub.put(userEntityStub.getEmail(), "111111");
        mapEmailOnInnStub.put(orderEntityStub2.getUser().getEmail(), "222222");

        when(orderedProductEntityRepositoryMock.findAll()).thenReturn(List.of(orderedProductEntityStub, orderedProductEntityStub2, orderedProductEntity3));
        when(orderRepositoryMock.findAll()).thenReturn(List.of(orderEntityStub, orderEntityStub2));
        when(exchangeServiceClientMock.getAllInnByEmail(anyList())).thenReturn(mapEmailOnInnStub);

        final Map<UUID, List<OrdersInfo>> actual = underTest.findOrdersInfoByProducts();

        verify(exchangeServiceClientMock).getAllInnByEmail(anyList());

        assertThat(actual.get(productEntityStub.getId()))
                .anySatisfy(ordersInfo ->
                {
                    assertEquals(orderEntityStub.getStatus(), ordersInfo.getStatus());
                    assertEquals(userEntityStub.getName(), ordersInfo.getUserName());
                    assertEquals(mapEmailOnInnStub.get(userEntityStub.getEmail()), ordersInfo.getUserInn());
                })
                .anySatisfy(ordersInfo ->
                {
                    assertEquals(orderEntityStub2.getStatus(), ordersInfo.getStatus());
                    assertEquals(orderEntityStub2.getUser().getName(), ordersInfo.getUserName());
                    assertEquals(mapEmailOnInnStub.get(orderEntityStub2.getUser().getEmail()), ordersInfo.getUserInn());
                });

        assertThat(actual.get(productEntityStub2.getId()))
                .anySatisfy(ordersInfo ->
                {
                    assertEquals(orderEntityStub2.getStatus(), ordersInfo.getStatus());
                    assertEquals(orderEntityStub2.getUser().getName(), ordersInfo.getUserName());
                    assertEquals(mapEmailOnInnStub.get(orderEntityStub2.getUser().getEmail()), ordersInfo.getUserInn());
                });
    }
}
