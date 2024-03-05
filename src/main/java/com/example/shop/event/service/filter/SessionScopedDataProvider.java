package com.example.shop.event.service.filter;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.UUID;

@Data
@Component
@SessionScope
@ToString
public class SessionScopedDataProvider {
    private UUID profileId;
}
