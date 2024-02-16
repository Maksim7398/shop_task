package com.example.shop.controller.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateNewsRequest {

    private String title;

    private String description;
}
