package com.example.shop.event.service;

import com.example.shop.controller.event.controller.request.EventSource;

public interface ProcessService {
     String processEvent(EventSource eventSource);
}
