package com.example.shop.service.document;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public interface FileService {
    List<String> getFilenames();

    File getFile(String fileName) throws FileNotFoundException;

    String uploadFile(MultipartFile file);
}
