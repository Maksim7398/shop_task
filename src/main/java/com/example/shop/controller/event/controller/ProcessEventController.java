package com.example.shop.controller.event.controller;

import com.example.shop.controller.event.controller.events.HttpEvent;
import com.example.shop.event.service.ProcessService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProcessEventController {

    private final ProcessService processService;

    @PostMapping("/process/events")
    public String processEvent(@RequestBody @Valid HttpEvent httpEvent) {
        System.out.println(httpEvent.getEvent());
        return processService.processEvent(httpEvent);
    }
}
