package com.example.shop.service.document;

import com.example.shop.exception.StorageException;
import com.example.shop.service.annotation.CheckTime;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

    @Value("${app.document.path}")
    private Path DOCUMENT_PATH;

    @CheckTime
    public List<String> getFilenames() {
        final File folder = new File(DOCUMENT_PATH + "/");
        if (folder.isDirectory() && folder.exists()) {
            final File[] files = folder.listFiles();
            return Arrays.stream(Objects.requireNonNull(files)).filter(File::isFile)
                    .map(File::getName).toList();
        }
        return List.of();
    }


    @CheckTime
    public Resource downloadFile(String fileName) {
        final File file = new File(DOCUMENT_PATH + File.separator + fileName);
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new StorageException(e.getMessage());
        }
        return new InputStreamResource(inputStream);
    }

    @Override
    public String uploadFile(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }
            final Path destinationFile = this.DOCUMENT_PATH.resolve(
                            Paths.get(Objects.requireNonNull(file.getOriginalFilename())))
                    .normalize().toAbsolutePath();
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
                return destinationFile.getFileName().toString();
            }
        } catch (IOException e) {
            throw new StorageException(e.getMessage());
        }
    }
}


