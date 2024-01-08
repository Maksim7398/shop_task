package com.example.shop.controller;

import com.example.shop.service.document.FileService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import java.io.File;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DocumentController {

    private final FileService fileService;

    @GetMapping("/documents")
    public List<String> getFileNames() {
        return fileService.getFilenames();
    }

    @GetMapping(value = "/downloadFile", produces = {"application/octet-stream"})
    @SneakyThrows
    public ResponseEntity<Resource> download(@RequestParam String fileName) {
        File file = fileService.getFile(fileName);
        Resource resource = new UrlResource(file.toURI());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDisposition(ContentDisposition.attachment().filename(file.getName()).build());
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        return fileService.uploadFile(file);
    }

}
