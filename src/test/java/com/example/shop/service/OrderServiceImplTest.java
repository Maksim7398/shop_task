package com.example.shop.service;

import com.example.shop.model.OrderBuilder;
import com.example.shop.model.OrdersInfo;
import com.example.shop.model.ProductEntityBuilder;
import com.example.shop.model.UserBuilder;
import com.example.shop.persist.entity.OrderEntity;
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

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyIterable;
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
        final UserEntity userEntity = UserBuilder.aUserEntity().build();
        final OrderEntity orderEntity = OrderBuilder.aOrderEntity().build();

        final List<String> innList = List.of("123456");

        when(orderRepositoryMock.findAllById(anyIterable())).thenReturn(List.of(orderEntity));
        when(exchangeServiceClient.getAllInnByEmail(anyList())).thenReturn(innList);

        final Map<UUID, Set<OrdersInfo>> ordersInfoMap = orderServiceMock.findOrdersInfoByProducts();

        verify(orderRepositoryMock).findAllById(anyIterable());

        assertEquals(ordersInfoMap.get(productEntity.getId()).stream().map(OrdersInfo::getStatus).toList().get(0)
                , orderEntity.getStatus());
        assertEquals(ordersInfoMap.get(productEntity.getId()).stream().map(OrdersInfo::getUserName).toList().get(0)
                , userEntity.getName());
        assertEquals(ordersInfoMap.get(productEntity.getId()).stream().map(OrdersInfo::getUserInn).toList().get(0)
                .replaceAll("]","")
               .replaceAll("\\[",""), innList.get(0));

        System.out.println(ordersInfoMap);
    }
}
