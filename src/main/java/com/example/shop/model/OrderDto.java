package com.example.shop.model;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
@Data
@Builder
public class OrderDto {

    private UUID id;
    private Status status;
    private BigDecimal orderPrice;
    private LocalDateTime createDate;

}
