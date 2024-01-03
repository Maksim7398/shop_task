package com.example.shop.controller;

import com.example.shop.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DocumentController {

    private final FileService fileService;

    @GetMapping("/file_names")
    public List<String> getFileNames() {
        return fileService.getFilenames();
    }

    @GetMapping(value = "/downloadFile", produces = {"application/octet-stream"})
    @SneakyThrows
    public ResponseEntity<byte[]> download(@RequestParam String fileName) {
        File file = fileService.getFile(fileName);
        byte[] contents = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDisposition(ContentDisposition.attachment().filename(file.getName()).build());
        return new ResponseEntity<>(contents, headers, HttpStatus.OK);
    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file){
       return fileService.uploadFile(file);
    }

}
