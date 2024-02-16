package com.example.shop.controller.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GetNewsResponse {

    private String title;

    private String description;

    private LocalDateTime createDate;
}
