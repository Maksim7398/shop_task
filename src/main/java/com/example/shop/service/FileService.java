package com.example.shop.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface FileService {
    List<String> getFilenames();

    File getFile(String fileName);

    String uploadFile(MultipartFile file);
}
