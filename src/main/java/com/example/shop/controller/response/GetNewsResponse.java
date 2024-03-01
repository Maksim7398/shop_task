package com.example.shop.controller.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class GetNewsResponse {

    private UUID id;

    private String title;

    private String description;

    private LocalDateTime createDate;
}
