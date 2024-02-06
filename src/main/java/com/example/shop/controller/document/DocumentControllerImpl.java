package com.example.shop.controller.document;

import com.example.shop.service.document.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DocumentControllerImpl implements DocumentController {

    private final FileService fileService;

    @Override
    public List<String> getFileNames() {
        return fileService.getFilenames();
    }

    @Override
    public ResponseEntity<Resource> download(@RequestParam String fileName) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDisposition(ContentDisposition.attachment().filename(fileName).build());

        return new ResponseEntity<>(fileService.downloadFile(fileName), headers, HttpStatus.OK);
    }

    @Override
    public String uploadFile(@RequestParam("file") MultipartFile file) {
      return fileService.uploadFile(file);
    }

}
