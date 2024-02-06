package com.example.shop.controller.document;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentController {
    @GetMapping("documents")
    List<String> getFileNames();

    @GetMapping(value = "/download", produces = {"application/octet-stream"})
    ResponseEntity<Resource> download(@RequestParam String fileName);

    @PostMapping("/upload")
    String uploadFile(@RequestParam("file") MultipartFile file);
}
