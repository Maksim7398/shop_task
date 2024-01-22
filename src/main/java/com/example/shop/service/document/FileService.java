package com.example.shop.service.document;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.util.List;

public interface FileService {
    List<String> getFilenames();

    Resource downloadFile(String fileName);

    String uploadFile(MultipartFile file);
}
