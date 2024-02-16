package com.example.shop.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
@Builder
public class NewsDto {

    private UUID id;

    private String title;

    private String description;

    private LocalDateTime createDate;
}
