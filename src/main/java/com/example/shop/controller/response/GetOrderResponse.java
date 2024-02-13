package com.example.shop.controller.response;

import com.example.shop.model.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
@Data
public class GetOrderResponse {

    private UUID id;

    private Status status;

    private BigDecimal orderPrice;

    @JsonFormat(locale = "ru", pattern = "dd MMMM yyyy")
    private LocalDateTime createDate;
}
