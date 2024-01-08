package com.example.shop.controller;

import com.example.shop.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DocumentController {

    private final FileService fileService;

    @GetMapping("/documents")
    public List<String> getFileNames(){
       return fileService.getFilenames();
    }

}
