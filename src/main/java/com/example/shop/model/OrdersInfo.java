package com.example.shop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class OrdersInfo {

    private UUID id;

    private Status status;

    private BigDecimal orderPrice;

    private LocalDateTime createDate;

    private String userName;

    private String userInn;

}
