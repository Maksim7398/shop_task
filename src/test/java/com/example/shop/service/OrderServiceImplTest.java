package com.example.shop.service;

import com.example.shop.model.OrderBuilder;
import com.example.shop.model.OrderedProductsEntityBuilder;
import com.example.shop.model.OrdersInfo;
import com.example.shop.model.ProductEntityBuilder;
import com.example.shop.model.UserBuilder;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        final UserEntity userEntity = UserBuilder.aUserEntity().build();
        final OrderEntity orderEntity = OrderBuilder.aOrderEntity().build();
        final OrderedProductEntity orderedProductEntity = OrderedProductsEntityBuilder.aOrderedProductEntity().build();
        final Map<String, String> mapEmailOnInn = new HashMap<>();
        mapEmailOnInn.put(userEntity.getEmail(), "111111");

        when(orderedProductEntityRepository.findAll()).thenReturn(List.of(orderedProductEntity));
        when(orderRepositoryMock.findAll()).thenReturn(List.of(orderEntity));
        when(exchangeServiceClient.getInnByEmail(userEntity.getEmail())).thenReturn(mapEmailOnInn.get(userEntity.getEmail()));

        final Map<UUID, List<OrdersInfo>> ordersInfoMap = orderServiceMock.findOrdersInfoByProducts();

        assertEquals(ordersInfoMap.get(productEntity.getId()).stream().map(OrdersInfo::getStatus).toList().get(0)
                , orderEntity.getStatus());
        assertEquals(ordersInfoMap.get(productEntity.getId()).stream().map(OrdersInfo::getUserName).toList().get(0)
                , userEntity.getName());
        assertEquals(ordersInfoMap.get(productEntity.getId()).stream().map(OrdersInfo::getUserInn).toList().get(0),
                mapEmailOnInn.get(userEntity.getEmail()));

        System.out.println(ordersInfoMap);
    }
}
