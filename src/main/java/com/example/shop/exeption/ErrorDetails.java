package com.example.shop.exeption;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ErrorDetails {

    private final String className;

    private final String message;

    private final LocalDateTime timeStamp;
}
