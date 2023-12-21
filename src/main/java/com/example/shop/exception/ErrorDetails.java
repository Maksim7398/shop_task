package com.example.shop.exception;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Builder
public class ErrorDetails {

    private final String className;

    private final String message;

    private final LocalDateTime timeStamp;

}
